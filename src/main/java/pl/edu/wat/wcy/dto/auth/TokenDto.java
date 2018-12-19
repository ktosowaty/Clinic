package pl.edu.wat.wcy.dto.auth;

public class TokenDto {
    private String token;

    public TokenDto() {}

    public TokenDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
