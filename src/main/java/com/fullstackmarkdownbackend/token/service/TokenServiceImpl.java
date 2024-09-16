package com.fullstackmarkdownbackend.token.service;

import com.fullstackmarkdownbackend.config.serutiry.MemberDetails;
import com.fullstackmarkdownbackend.config.serutiry.service.MemberDetailsService;
import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import com.fullstackmarkdownbackend.token.dto.res.TokenResponse;
import com.fullstackmarkdownbackend.token.entity.JWTEntity;
import com.fullstackmarkdownbackend.token.entity.RefreshTokenEntity;
import com.fullstackmarkdownbackend.token.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import static com.fullstackmarkdownbackend.base.vo.TokenType.ACCESS_TOKEN;
import static com.fullstackmarkdownbackend.constants.ApplicationConstants.REFRESH_TOKEN_COOKIE;
import static com.fullstackmarkdownbackend.constants.ApplicationConstants.REFRESH_TOKEN_SUBJECT;

/**
 * packageName    : com.fullstackmarkdownbackend.provider.token.service
 * fileName       : TokenServiceImpl
 * author         : AngryPig123
 * date           : 24. 9. 17.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 17.        AngryPig123       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    private final MemberDetailsService memberDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    private Map<String, Object> authenticationClaimHelper(Authentication authentication) {
        Map<String, Object> claim = new HashMap<>();
        claim.put("loginId", authentication.getName());
        claim.put("authorities", authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","))
        );
        return claim;
    }

    @Override
    public TokenResponse accessTokenIssue(@NotNull Authentication authentication) {
        Map<String, Object> claim = authenticationClaimHelper(authentication);
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        JWTEntity jwtEntity = JWTEntity.initAccessToken(SECRET_KEY, claim);
        JwtBuilder jwtBuilder = Jwts.builder()
                .issuer(jwtEntity.getIssuer())
                .subject(jwtEntity.getSubject());
        claim.forEach((key, value) -> jwtBuilder.claim(key, value.toString()));
        String accessToken = jwtBuilder
                .issuedAt(jwtEntity.getIssuedAt())
                .expiration(jwtEntity.getExpiration())
                .signWith(secretKey)
                .compact();
        return new TokenResponse(ACCESS_TOKEN, accessToken);
    }

    @Override
    public Authentication validateToken(@NotNull String token, HttpServletRequest request) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(secretKey).build()
                    .parseSignedClaims(token)
                    .getPayload();
            String username = claims.get("loginId", String.class);
            String authoritiesString = claims.get("authorities", String.class);
            String[] authorities = StringUtils.tokenizeToStringArray(authoritiesString, ",");
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities.length);
            for (String authority : authorities) {
                grantedAuthorities.add(new SimpleGrantedAuthority(authority));
            }
            return new UsernamePasswordAuthenticationToken(username, null, grantedAuthorities);
        } catch (ExpiredJwtException e) {
            String refreshToken = request.getHeader(REFRESH_TOKEN_COOKIE);
            if (Objects.nonNull(refreshToken)) {
                RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findRefreshTokenEntityByRefreshToken(refreshToken)
                        .orElseThrow(() -> new RuntimeException("todo jwt refresh token not valid"));
                Jwts.parser()
                        .verifyWith(secretKey).build()
                        .parseSignedClaims(refreshTokenEntity.getRefreshToken())
                        .getPayload();
                MemberEntity memberEntity = refreshTokenEntity.getMemberEntity();
                MemberDetails memberDetails = (MemberDetails) memberDetailsService.loadUserByUsername(memberEntity.getLoginId());
                return new UsernamePasswordAuthenticationToken(memberEntity.getLoginId(), null, memberDetails.getAuthorities());
            }
            throw new RuntimeException();
        } catch (JwtException e) {
            //  todo refresh 토큰도 만료된 상태임으로 재로그인을 시킨다.
            throw new RuntimeException();
        }
    }

    @Override
    public TokenResponse refreshTokenIssue(@NotNull Authentication authentication) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        JWTEntity jwtEntity = JWTEntity.initRefreshToken(SECRET_KEY);
        JwtBuilder jwtBuilder = Jwts.builder()
                .issuer(jwtEntity.getIssuer())
                .subject(jwtEntity.getSubject());
        String refreshToken = jwtBuilder
                .issuedAt(jwtEntity.getIssuedAt())
                .expiration(jwtEntity.getExpiration())
                .signWith(secretKey)
                .compact();
        return new TokenResponse(ACCESS_TOKEN, refreshToken);
    }

    @Override
    public void insertRefreshToken(MemberEntity memberEntity, String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.insertInit(memberEntity, refreshToken);
        refreshTokenRepository.save(refreshTokenEntity);
    }

}
