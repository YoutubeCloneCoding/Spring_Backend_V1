package com.anys34.youtube.domain.User.domain.repository;

import com.anys34.youtube.domain.User.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
