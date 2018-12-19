package pl.edu.wat.wcy.service.token;

public interface TokenGenerator {
    String expiring(long userId, String username, String role);
}
