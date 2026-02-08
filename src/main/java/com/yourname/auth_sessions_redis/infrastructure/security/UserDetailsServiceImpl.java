package com.yourname.auth_sessions_redis.infrastructure.security;

import com.yourname.auth_sessions_redis.user.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yourname.auth_sessions_redis.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) {
        User user = userService.getUser(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDetailsImpl(user);
    }

}
