package pl.edu.wat.wcy.security;

import io.jsonwebtoken.Clock;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class SystemClock implements Clock {
    @Override
    public Date now() {
        return Date.from(Instant.now());
    }
}
