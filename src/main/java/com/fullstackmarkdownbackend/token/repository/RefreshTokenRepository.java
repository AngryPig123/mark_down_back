package com.fullstackmarkdownbackend.token.repository;

import com.fullstackmarkdownbackend.token.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : com.fullstackmarkdownbackend.token.repository
 * fileName       : RefreshTokenRepository
 * author         : AngryPig123
 * date           : 24. 9. 17.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 17.        AngryPig123       최초 생성
 */
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {

    Optional<RefreshTokenEntity> findRefreshTokenEntityByRefreshToken(String refreshToken);

}
