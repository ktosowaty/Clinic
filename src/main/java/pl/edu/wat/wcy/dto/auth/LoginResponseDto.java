package pl.edu.wat.wcy.dto.auth;

import pl.edu.wat.wcy.model.person.user.Email;
import pl.edu.wat.wcy.model.person.user.UserType;
import pl.edu.wat.wcy.model.person.user.Username;

public class LoginResponseDto {
    private Username username;

    private Email email;

    private UserType userType;

    private long personId;

    private String token;

    private LoginResponseDto() {}

    public LoginResponseDto(Username username, Email email, UserType userType, long personId, String token) {
        this.username = username;
        this.email = email;
        this.userType = userType;
        this.personId = personId;
        this.token = token;
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }

    public long getPersonId() {
        return personId;
    }

    public String getToken() {
        return token;
    }
}
