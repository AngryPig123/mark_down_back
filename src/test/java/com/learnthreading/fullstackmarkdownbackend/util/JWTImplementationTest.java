package com.learnthreading.fullstackmarkdownbackend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * packageName    : com.learnthreading.fullstackmarkdownbackend.util
 * fileName       : JWTImplementationTest
 * author         : AngryPig123
 * date           : 24. 9. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 2.        AngryPig123       최초 생성
 */

@Slf4j
public class JWTImplementationTest {

    private final String JWT_SECRET = "dhjoi912y7uc890JAPoiru1878dsjOPUdaslkHKLJ123A";
    private final static Collection<? extends GrantedAuthority> AUTHORITIES;

    static {
        GrantedAuthority create = new SimpleGrantedAuthority("CREATE");
        GrantedAuthority read = new SimpleGrantedAuthority("READ");
        GrantedAuthority update = new SimpleGrantedAuthority("UPDATE");
        GrantedAuthority delete = new SimpleGrantedAuthority("DELETE");
        AUTHORITIES = List.of(create, read, update, delete);
    }

    private Collection<? extends GrantedAuthority> getAuthorities() {
        return AUTHORITIES;
    }

    @Test
    void jwtGenerationAndClaimsTest() {

        SecretKey secretKey = Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder()
                .issuer("jwt generation")
                .subject("JWT")
                .claim("username", "admin")
                .claim("authorities", getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 30000000))
                .signWith(secretKey)
                .compact();

        Claims claims = Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
        String username = claims.get("username", String.class);
        String authorities = claims.get("authorities", String.class);

        log.info("username = {}, authorities = {}", username, authorities);

    }

}
