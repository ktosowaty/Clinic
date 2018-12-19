package pl.edu.wat.wcy.dto.user;

import pl.edu.wat.wcy.model.person.user.UserType;

public class UserRequestDto {
    private String username;

    private String plainPassword;

    private String email;

    private UserType userType;

    private UserRequestDto() {}

    public UserRequestDto(String username, String plainPassword, String email, UserType userType) {
        this.username = username;
        this.plainPassword = plainPassword;
        this.email = email;
        this.userType = userType;
    }

    public String getUsername() {
        return username;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public String getEmail() {
        return email;
    }

    public UserType getUserType() {
        return userType;
    }
}
