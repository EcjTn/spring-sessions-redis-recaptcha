package com.yourname.auth_sessions_redis.auth.dto;

import jakarta.validation.constraints.NotNull;

public record AuthLoginDto(
        @NotNull
        String username,
        @NotNull
        String password
) {}
