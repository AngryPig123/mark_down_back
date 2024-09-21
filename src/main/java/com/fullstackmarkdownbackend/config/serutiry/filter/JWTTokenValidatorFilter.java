package com.fullstackmarkdownbackend.config.serutiry.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackmarkdownbackend.advice.GlobalExceptionResponseBody;
import com.fullstackmarkdownbackend.token.service.TokenService;
import com.fullstackmarkdownbackend.util.servlet.HttpServletException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.fullstackmarkdownbackend.constants.ApplicationConstants.JWT_HEADER;
import static com.fullstackmarkdownbackend.constants.ApplicationConstants.LOGIN_API_PATH;
import static com.fullstackmarkdownbackend.util.servlet.HttpHeaderUtil.getAuthorizationHeader;

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
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        String header = request.getHeader(JWT_HEADER);
        String jwt;

        try {
            jwt = getAuthorizationHeader(header);
        } catch (HttpServletException httpServletException) {
            jwtExceptionResponseHelper(response, url, httpServletException.getHttpStatus());
            return;
        }

        if (Objects.nonNull(jwt)) {
            try {
                Authentication authentication = tokenService.validateToken(jwt, request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception exception) {
                jwtExceptionResponseHelper(response, url, HttpStatus.UNAUTHORIZED);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals(LOGIN_API_PATH);
    }

    //  나중에 유틸로 뺸다.
    private void jwtExceptionResponseHelper(HttpServletResponse response, String url, HttpStatus httpStatus) throws IOException {
        response.setHeader("api-error-reason", httpStatus.getReasonPhrase());
        response.setStatus(httpStatus.value());
        GlobalExceptionResponseBody globalExceptionResponseBody = new GlobalExceptionResponseBody(
                url, httpStatus, LocalDateTime.now(), httpStatus.getReasonPhrase()
        );
        response.setContentType("application/json; charset=UTF-8");
        String jsonResponse = objectMapper.writeValueAsString(globalExceptionResponseBody);
        response.getWriter().write(jsonResponse);
    }

}
