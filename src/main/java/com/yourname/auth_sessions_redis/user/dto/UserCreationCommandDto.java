package com.yourname.auth_sessions_redis.user.dto;

public record UserCreationCommandDto(
        String username,
        String password
) {}
