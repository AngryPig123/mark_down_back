package com.fullstackmarkdownbackend.token.service;

import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import com.fullstackmarkdownbackend.token.dto.res.TokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

/**
 * packageName    : com.fullstackmarkdownbackend.provider.token.service
 * fileName       : TokenService
 * author         : AngryPig123
 * date           : 24. 9. 17.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 17.        AngryPig123       최초 생성
 */
public interface TokenService {

    TokenResponse accessTokenIssue(Authentication authentication);

    Authentication validateToken(String token, HttpServletRequest request);

    TokenResponse refreshTokenIssue(Authentication authentication);

    void insertRefreshToken(MemberEntity memberEntity, String refreshToken);

    TokenResponse validateAndReissueRefreshToken(String loginId);

}
