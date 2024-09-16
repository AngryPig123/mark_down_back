package com.fullstackmarkdownbackend.constants;

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

    //  JWT
    public static final String JWT_HEADER = "Authorization";
    public static final String ISSUER = "ANGRY_PIG";
    public static final String ACCESS_TOKEN_SUBJECT = "access_token";
    public static final String REFRESH_TOKEN_SUBJECT = "refresh_token";
    public static final Long ACCESS_TOKEN_EXPIRES_IN = 10L;
    public static final Long REFRESH_TOKEN_EXPIRES_IN = 60L;

    //  cookie
    public static final String REFRESH_TOKEN_COOKIE = "refresh_token";

}
