package com.fullstackmarkdownbackend.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackmarkdownbackend.advice.GlobalExceptionResponseBody;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * packageName    : com.fullstackmarkdownbackend.config.serutiry.filter
 * fileName       : ProjectAuthenticationEntryPointFilter
 * author         : AngryPig123
 * date           : 24. 9. 18.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 18.        AngryPig123       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ProjectAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        String url = request.getRequestURI();
        response.setHeader("api-error-reason", "Authentication failed");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        GlobalExceptionResponseBody globalExceptionResponseBody = new GlobalExceptionResponseBody(
                url, HttpStatus.UNAUTHORIZED, LocalDateTime.now(), authException.getMessage()
        );
        response.setContentType("application/json;chart=UTF-8");
        String jsonResponse = objectMapper.writeValueAsString(globalExceptionResponseBody);
        response.getWriter().write(jsonResponse);

    }

}
