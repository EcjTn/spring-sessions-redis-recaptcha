package com.yourname.auth_sessions_redis.infrastructure.security.recaptcha;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class RecaptchaService {

    @Value("${recaptcha.secret}")
    private String secretKey;
    private WebClient webClient;

    public RecaptchaService(WebClient webClient) {
        this.webClient = webClient;
    }

    public boolean validate(String token) {
        RecaptchaResponseDto response = webClient
                .post()
                .uri(ub -> ub
                        .scheme("https")
                        .host("www.google.com")
                        .path("/recaptcha/api/siteverify")
                        .queryParam("secret", secretKey)
                        .queryParam("response", token)
                        .build())
                .retrieve()
                .bodyToMono(RecaptchaResponseDto.class)
                .block();

        return response != null && response.success();
    }

}
