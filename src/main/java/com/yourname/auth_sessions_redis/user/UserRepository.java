package com.yourname.auth_sessions_redis.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yourname.auth_sessions_redis.user.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public boolean existsByUsername(String username);
    public Optional<User> findByUsername(String username);
}
