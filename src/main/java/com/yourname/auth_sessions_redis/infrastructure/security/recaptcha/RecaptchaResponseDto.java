package com.yourname.auth_sessions_redis.infrastructure.security.recaptcha;

public record RecaptchaResponseDto(
        boolean success,
        String hostname
) {}
