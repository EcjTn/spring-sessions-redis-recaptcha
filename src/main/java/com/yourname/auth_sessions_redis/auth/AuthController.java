package com.yourname.auth_sessions_redis.auth;


import com.yourname.auth_sessions_redis.auth.dto.AuthLoginDto;
import com.yourname.auth_sessions_redis.auth.dto.AuthRegisterDto;
import com.yourname.auth_sessions_redis.common.dto.MessageResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto register(@RequestBody @Valid AuthRegisterDto user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public MessageResponseDto login(@RequestBody @Valid AuthLoginDto user, HttpServletRequest request) {

        Authentication userAuth = authService.login(user);

        HttpSession session = request.getSession(true);

        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(userAuth);

        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);

        return new MessageResponseDto("Successfully logged in.");
    }

}
