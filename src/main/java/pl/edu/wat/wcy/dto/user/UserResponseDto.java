package pl.edu.wat.wcy.dto.user;

import pl.edu.wat.wcy.model.person.user.Email;
import pl.edu.wat.wcy.model.person.user.UserType;
import pl.edu.wat.wcy.model.person.user.Username;

public class UserResponseDto {
    private Username username;

    private Email email;

    private UserType userType;

    private UserResponseDto() {}

    public UserResponseDto(Username username, Email email, UserType userType) {
        this.username = username;
        this.email = email;
        this.userType = userType;
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
}
