package pl.edu.wat.wcy.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.wat.wcy.model.person.user.UserType;

import java.util.Collection;
import java.util.Collections;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

public class AuthenticatedUser implements UserDetails {
    private long id;

    private String username;

    private UserType userType;

    public AuthenticatedUser(long id, String username, String role) {
        this.id = id;
        this.username = requireNonNull(username, "username");
        this.userType = UserType.valueOf(role);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(userType.name()));
    }

    public long getId() {
        return id;
    }

    public UserType getUserType() {
        return userType;
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
