package com.yourname.auth_sessions_redis.infrastructure.security;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.yourname.auth_sessions_redis.user.model.User;

public class UserDetailsImpl implements UserDetails, Serializable {

    private final String username;
    private final String role;
    private final String password;
    private final Long userId;

    public UserDetailsImpl(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole().name();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Long getUserId() {
        return this.userId;
    }

}
