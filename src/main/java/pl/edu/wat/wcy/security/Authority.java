package pl.edu.wat.wcy.security;

import org.springframework.security.core.GrantedAuthority;
import pl.edu.wat.wcy.model.person.account.UserType;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

public class Authority implements GrantedAuthority {
    private String role;

    public Authority(UserType userType) {
        this.role = requireNonNull(userType, "user type").name();
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
