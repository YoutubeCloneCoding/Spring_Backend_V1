package com.anys34.youtube.domain.auth.domain.repository;

import com.anys34.youtube.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
    Optional<RefreshToken> findByUserId(Long userId);
}
