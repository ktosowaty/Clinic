package pl.edu.wat.wcy.security;

public interface TokenGenerator {
    String expiring(long userId, String username);
}
