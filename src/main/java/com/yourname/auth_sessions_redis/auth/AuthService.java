package com.yourname.auth_sessions_redis.auth;

import com.yourname.auth_sessions_redis.auth.dto.AuthLoginDto;
import com.yourname.auth_sessions_redis.auth.dto.AuthRegisterDto;
import com.yourname.auth_sessions_redis.common.dto.MessageResponseDto;
import com.yourname.auth_sessions_redis.common.exception.ResourceConflictException;
import com.yourname.auth_sessions_redis.common.exception.ValidationException;
import com.yourname.auth_sessions_redis.infrastructure.security.recaptcha.RecaptchaService;
import com.yourname.auth_sessions_redis.user.UserService;
import com.yourname.auth_sessions_redis.user.dto.UserCreationCommandDto;



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private RecaptchaService recaptchaService;
    private AuthenticationManager authenticationManager;

    public AuthService(
        UserService userService,
        PasswordEncoder passwordEncoder,
        RecaptchaService recaptchaService,
        AuthenticationManager authenticationManager
    ) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.recaptchaService = recaptchaService;
        this.authenticationManager = authenticationManager;
    }

    public MessageResponseDto register(AuthRegisterDto user) {
        if(!recaptchaService.validate(user.recaptchaToken())) throw new ValidationException("Recaptcha validation failed");
        if(userService.isUsernameTaken(user.username())) throw new ResourceConflictException("User already exists");

        String hashPassword = passwordEncoder.encode(user.password());
        userService.createUser(new UserCreationCommandDto(user.username(), hashPassword));

        return new MessageResponseDto("Successfully registered.");
    }

    public Authentication login(AuthLoginDto user){
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user.username(), user.password());
        return authenticationManager.authenticate(authToken);
    }


}
