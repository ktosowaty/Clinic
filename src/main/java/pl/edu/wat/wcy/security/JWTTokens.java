package pl.edu.wat.wcy.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;
import static io.jsonwebtoken.impl.TextCodec.BASE64;
import static pl.edu.wat.wcy.util.Validator.requireNonNegative;
import static pl.edu.wat.wcy.util.Validator.requireNonNull;

@Service
public class JWTTokens implements TokenVerifier, TokenGenerator {
    private static final GzipCompressionCodec COMPRESSION_CODEC = new GzipCompressionCodec();

    private final Clock clock;

    private final String issuer;

    private final int expirationSec;

    private final int clockSkewSec;

    private final String secretKey;

    @Autowired
    JWTTokens(final Clock clock,
              @Value("${jwt.issuer}") final String issuer,
              @Value("${jwt.expiration-sec}") final int expirationSec,
              @Value("${jwt.clock-skew-sec}") final int clockSkewSec,
              @Value("${jwt.secret}") final String secret) {
        this.clock = requireNonNull(clock, "clock");
        this.issuer = requireNonNull(issuer, "issuer");
        this.expirationSec = requireNonNegative(expirationSec, "expiration sec");
        this.clockSkewSec = requireNonNegative(clockSkewSec, "clock skew sec");
        this.secretKey = BASE64.encode(requireNonNull(secret, "secret"));
    }

    @Override
    public String expiring(long userId, String username) {
        requireNonNull(username, "username");
        return newToken(String.valueOf(userId), username, expirationSec);
    }

    private String newToken(String id, String subject, final int expiresInSec) {
        final Date now = clock.now();
        final Claims claims = Jwts
                .claims()
                .setId(id)
                .setSubject(subject)
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(calculateExpiration(now, expiresInSec));
        return Jwts
                .builder()
                .setClaims(claims)
                .signWith(HS256, secretKey)
                .compressWith(COMPRESSION_CODEC)
                .compact();
    }

    private Date calculateExpiration(Date now, int expiresInSec) {
        Instant expiresAt = now
                .toInstant()
                .plusSeconds(expiresInSec);
        return Date.from(expiresAt);
    }

    @Override
    public Optional<AuthenticatedUser> verify(final String token) {
        try {
            final JwtParser parser = Jwts
                    .parser()
                    .requireIssuer(issuer)
                    .setClock(clock)
                    .setAllowedClockSkewSeconds(clockSkewSec)
                    .setSigningKey(secretKey);
            Claims claims = parser.parseClaimsJws(token).getBody();
            return Optional.of(toUser(claims));
        } catch (IllegalArgumentException | JwtException e) {
            // todo: logger
            return Optional.empty();
        }
    }

    private AuthenticatedUser toUser(Claims claims) {
        long id = Long.parseLong(claims.getId());
        String username = claims.getSubject();
        return new AuthenticatedUser(id, username);
    }
}
