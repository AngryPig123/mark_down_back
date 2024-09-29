package com.fullstackmarkdownbackend.config.security.filter;

import com.fullstackmarkdownbackend.token.dto.res.TokenResponse;
import com.fullstackmarkdownbackend.token.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

import static com.fullstackmarkdownbackend.constants.ApplicationConstants.*;

/**
 * packageName    : com.fullstackmarkdownbackend.config.serutiry.filter
 * fileName       : JWTTokenGeneratorFilter
 * author         : AngryPig123
 * date           : 24. 9. 16.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 16.        AngryPig123       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    /**
     * iss: 토큰 발급자(issuer)
     * sub: 토큰 제목(subject)
     * aud: 토큰 대상자(audience)
     * exp: 토큰 만료 시간(expiration), NumericDate 형식으로 되어 있어야 함 ex) 1480849147370
     * nbf: 토큰 활성 날짜(not before), 이 날이 지나기 전의 토큰은 활성화되지 않음
     * iat: 토큰 발급 시간(issued at), 토큰 발급 이후의 경과 시간을 알 수 있음
     * jti: JWT 토큰 식별자(JWT ID), 중복 방지를 위해 사용하며, 일회용 토큰(Access Token) 등에 사용
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (Objects.nonNull(authentication)) {
            TokenResponse tokenResponse = tokenService.accessTokenIssue(authentication);
            response.setHeader(JWT_HEADER, tokenResponse.getTokenValue());
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        //  return !request.getServletPath().equals(LOGIN_API_PATH);
        return true;
    }

}
