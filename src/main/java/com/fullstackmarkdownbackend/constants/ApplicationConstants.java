package com.fullstackmarkdownbackend.constants;

import static com.fullstackmarkdownbackend.version.VersionConstants.VERSION;

/**
 * packageName    : com.fullstackmarkdownbackend.constants
 * fileName       : ApplicationConstants
 * author         : AngryPig123
 * date           : 24. 9. 16.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 16.        AngryPig123       최초 생성
 */
public class ApplicationConstants {

    //  login api path
    public static final String LOGIN_API_PATH = String.format("%s/login/**", VERSION); //  "/api/v1", "/api/v1/login"

    //  join api path
    public static final String JOIN_API_PATH = String.format("%s/join/**", VERSION); //  "/api/v1", "/api/v1/join"

    //  join api path
    public static final String CSRF_API_PATH = String.format("%s/security/csrf", VERSION); //  "/api/v1", "/api/v1/csrf"
    //  /api/v1/security/csrf
    //  JWT
    public static final String JWT_HEADER = "Authorization";
    public static final String ISSUER = "ANGRY_PIG";
    public static final String ACCESS_TOKEN_SUBJECT = "access_token";
    public static final String REFRESH_TOKEN_SUBJECT = "refresh_token";
    public static final Long ACCESS_TOKEN_EXPIRES_IN = 10L;
    public static final Long REFRESH_TOKEN_EXPIRES_IN = 60L;

    //  cookie
    public static final String REFRESH_TOKEN_COOKIE = "refresh_token";

    //  csrf
    public static final String CSRF_TOKEN_HEADER = "X-CSRF-TOKEN";


}
