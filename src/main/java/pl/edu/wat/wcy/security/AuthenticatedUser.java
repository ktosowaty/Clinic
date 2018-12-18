package pl.edu.wat.wcy.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

import static pl.edu.wat.wcy.util.Validator.requireNonNull;

public class AuthenticatedUser implements UserDetails {
    private long id;

    private String username;

    //private UserType userType;

    public AuthenticatedUser(long id, String username/*, UserType userType*/) {
        this.id = id;
        this.username = requireNonNull(username, "username");
        //this.userType = requireNonNull(userType, "user type");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //List<UserType> list = new ArrayList<>();
        //list.add(userType);
        //return list;
        return Collections.EMPTY_LIST;
    }

    public long getId() {
        return id;
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
