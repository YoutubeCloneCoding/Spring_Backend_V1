package com.anys34.youtube.domain.auth.domain.repository;

import com.anys34.youtube.domain.auth.domain.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByEmail(Long userId);
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
