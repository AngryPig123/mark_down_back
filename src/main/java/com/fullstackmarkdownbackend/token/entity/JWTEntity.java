package com.fullstackmarkdownbackend.token.entity;

import lombok.Getter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

import static com.fullstackmarkdownbackend.constants.ApplicationConstants.*;

/**
 * packageName    : com.fullstackmarkdownbackend.provider.token.entity
 * fileName       : JWTEntity
 * author         : AngryPig123
 * date           : 24. 9. 17.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 17.        AngryPig123       최초 생성
 */
@Getter
public class JWTEntity {

    private final String jwtSecretValue;
    private final String issuer;
    private final String subject;
    private final Map<String, Object> claim;
    private final Date issuedAt;
    private final Date expiration;

    private JWTEntity(String jwtSecretValue, String issuer, String subject, Map<String, Object> claim, Date issuedAt, Date expiration) {
        this.jwtSecretValue = jwtSecretValue;
        this.issuer = issuer;
        this.subject = subject;
        this.claim = claim;
        this.issuedAt = issuedAt;
        this.expiration = expiration;
    }

    public static JWTEntity initAccessToken(String jwtSecretValue, Map<String, Object> claim) {
        return new JWTEntity(
                jwtSecretValue,
                ISSUER,
                ACCESS_TOKEN_SUBJECT,
                claim,
                Timestamp.valueOf(LocalDateTime.now()),
                Date.from(Instant.now().plus(ACCESS_TOKEN_EXPIRES_IN, ChronoUnit.MINUTES))
        );
    }

    public static JWTEntity initRefreshToken(String jwtSecretValue) {
        return new JWTEntity(
                jwtSecretValue,
                ISSUER,
                REFRESH_TOKEN_SUBJECT,
                Map.of(),
                Timestamp.valueOf(LocalDateTime.now()),
                Date.from(Instant.now().plus(REFRESH_TOKEN_EXPIRES_IN, ChronoUnit.MINUTES))
        );
    }

}
