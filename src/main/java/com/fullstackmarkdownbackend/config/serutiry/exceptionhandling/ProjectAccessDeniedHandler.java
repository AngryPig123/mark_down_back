package com.fullstackmarkdownbackend.config.serutiry.exceptionhandling;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullstackmarkdownbackend.advice.GlobalExceptionResponseBody;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.log.LogMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * packageName    : com.fullstackmarkdownbackend.config.serutiry.exceptionhandling
 * fileName       : ProjectAccessDeniedHandler
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
public class ProjectAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        String url = request.getRequestURI();
        response.setHeader("api-error-reason", "Authorization failed");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        GlobalExceptionResponseBody globalExceptionResponseBody = new GlobalExceptionResponseBody(
                url, HttpStatus.FORBIDDEN, LocalDateTime.now(), accessDeniedException.getMessage()
        );
        response.setContentType("application/json;chart=UTF-8");
        String jsonResponse = objectMapper.writeValueAsString(globalExceptionResponseBody);
        response.getWriter().write(jsonResponse);

    }

}
