package com.yourname.auth_sessions_redis.infrastructure.web;

import com.yourname.auth_sessions_redis.common.exception.ResourceConflictException;
import com.yourname.auth_sessions_redis.common.exception.ResourceNotFoundException;
import com.yourname.auth_sessions_redis.common.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class, UsernameNotFoundException.class})
    public ProblemDetail handleNotFound(RuntimeException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        pd.setTitle("Resource not found.");
        pd.setDetail(ex.getMessage());
        return pd;
    }

    @ExceptionHandler(ResourceConflictException.class)
    public ProblemDetail handleConflict(RuntimeException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        pd.setTitle("Resource conflict");
        pd.setDetail(ex.getMessage());
        return pd;
    }

    @ExceptionHandler(ValidationException.class)
    public ProblemDetail handleValidation(ValidationException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        pd.setTitle("Validation failed");
        pd.setDetail(ex.getMessage());
        return pd;
    }

}
