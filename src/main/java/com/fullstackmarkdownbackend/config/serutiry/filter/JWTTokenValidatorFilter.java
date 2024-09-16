package com.fullstackmarkdownbackend.config.serutiry.filter;

import com.fullstackmarkdownbackend.token.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

import static com.fullstackmarkdownbackend.constants.ApplicationConstants.*;

/**
 * packageName    : com.fullstackmarkdownbackend.config.serutiry.filter
 * fileName       : JWTTokenValidatorFilter
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
public class JWTTokenValidatorFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JWT_HEADER);
        if (Objects.nonNull(jwt)) {
            try {
                Authentication authentication = tokenService.validateToken(jwt, request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception exception) {
                throw new BadCredentialsException("in valid token received");
            }
        }
        filterChain.doFilter(request, response);
    }

    //  login api path 가 아닌경우 실행되면 안된다
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/login"/* todo login api path */);
    }

}
