package com.fullstackmarkdownbackend.util.servlet;

import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.function.Function;

/**
 * packageName    : com.fullstackmarkdownbackend.util
 * fileName       : HttpHeaderUtil
 * author         : AngryPig123
 * date           : 24. 9. 21.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 21.        AngryPig123       최초 생성
 */
public class HttpHeaderUtil {

    private static final Function<String, String> SET_AUTHORIZATION_HEADER_HELPER = (token) -> String.format("Bearer %s", token);
    private static final Function<String, String> GET_AUTHORIZATION_HEADER_HELPER = (authorizationValue) -> {
        String bearer = "Bearer ";
        boolean contains = authorizationValue.contains(bearer);
        if (!contains) throw new HttpServletException("Authorization header not found", HttpStatus.UNAUTHORIZED);
        return authorizationValue.replace("Bearer ", "");
    };

    public static String setAuthorizationHeader(String token) {
        return SET_AUTHORIZATION_HEADER_HELPER.apply(token);
    }

    public static String getAuthorizationHeader(String authorizationValue) {
        if (Objects.isNull(authorizationValue))
            throw new HttpServletException("authorizationValue is not null", HttpStatus.BAD_REQUEST);
        return GET_AUTHORIZATION_HEADER_HELPER.apply(authorizationValue);
    }

}
