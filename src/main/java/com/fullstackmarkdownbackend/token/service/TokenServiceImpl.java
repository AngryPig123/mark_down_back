package com.fullstackmarkdownbackend.token.service;

import com.fullstackmarkdownbackend.config.security.MemberDetails;
import com.fullstackmarkdownbackend.config.security.service.MemberDetailsService;
import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import com.fullstackmarkdownbackend.member.repository.MemberRepository;
import com.fullstackmarkdownbackend.token.dto.res.TokenResponse;
import com.fullstackmarkdownbackend.token.entity.JWTEntity;
import com.fullstackmarkdownbackend.token.entity.RefreshTokenEntity;
import com.fullstackmarkdownbackend.token.repository.RefreshTokenRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.fullstackmarkdownbackend.base.vo.TokenType.ACCESS_TOKEN;
import static com.fullstackmarkdownbackend.base.vo.TokenType.REFRESH_TOKEN;

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

    private final MemberRepository memberRepository;
    private final MemberDetailsService memberDetailsService;
    private final RefreshTokenRepository refreshTokenRepository;

    @Value("${jwt.secret-key}")
    private String SECRET_KEY;

    @Override
    public TokenResponse accessTokenIssue(@NotNull Authentication authentication) {
        Map<String, Object> claim = authenticationClaimHelper(authentication);
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        JWTEntity jwtEntity = JWTEntity.initAccessToken(SECRET_KEY, claim);
        JwtBuilder jwtBuilder = Jwts.builder()
                .issuer(jwtEntity.getIssuer())
                .subject(jwtEntity.getSubject());
        jwtEntity.getClaim().forEach((key, value) -> jwtBuilder.claim(key, value.toString()));
        String accessToken = jwtBuilder
                .issuedAt(jwtEntity.getIssuedAt())
                .expiration(jwtEntity.getExpiration())
                .signWith(secretKey)
                .compact();
        return new TokenResponse(ACCESS_TOKEN, accessToken);
    }

    @Override
    public Authentication validateToken(@NotNull String accessToken, HttpServletRequest request) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        try {
            Claims claims = tokenParserHelper(secretKey, accessToken);
            return authenticationInfoHelper(claims);
        } catch (ExpiredJwtException expiredJwtException) {
            Claims exceptionClaims = expiredJwtException.getClaims();
            String loginId = exceptionClaims.get("loginId", String.class);
            MemberDetails memberDetails = (MemberDetails) memberDetailsService.loadUserByUsername(loginId);
            Claims claims = tokenParserHelper(secretKey, memberDetails.getMemberRefreshToken().getTokenValue());
            return authenticationInfoHelper(claims);
        } catch (JwtException jwtException) {
            throw new BadCredentialsException("Invalid JWT token: " + jwtException.getMessage(), jwtException);
        } catch (Exception exception) {
            throw new AuthenticationServiceException("An unexpected error occurred while validating the token.", exception);
        }
    }

    @Override
    public TokenResponse refreshTokenIssue(@NotNull Authentication authentication) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        String refreshToken = issueRefreshToken(secretKey);
        return new TokenResponse(ACCESS_TOKEN, refreshToken);
    }

    @Override
    public void insertRefreshToken(MemberEntity memberEntity, String refreshToken) {
        RefreshTokenEntity refreshTokenEntity = RefreshTokenEntity.insertInit(memberEntity, refreshToken);
        refreshTokenRepository.save(refreshTokenEntity);
    }

    @Override
    @Transactional
    public TokenResponse validateAndReissueRefreshToken(String loginId) {
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        MemberEntity member = memberRepository.findMemberEntityByLoginId(loginId).orElseThrow(RuntimeException::new);
        RefreshTokenEntity refreshTokenEntity = member.getRefreshTokenEntity();
        String refreshToken = refreshTokenEntity.getRefreshToken();
        try {
            tokenParserHelper(secretKey, refreshTokenEntity.getRefreshToken());
        } catch (ExpiredJwtException expiredJwtException) {
            refreshToken = issueRefreshToken(secretKey);
            refreshTokenEntity.refreshTokenUpdate(refreshToken);
        }
        return new TokenResponse(REFRESH_TOKEN, refreshToken);
    }

    /**
     * throw ExpiredJwtException
     *
     * @param secretKey
     * @param getRefreshToken
     * @return
     */
    private static Claims tokenParserHelper(SecretKey secretKey, String getRefreshToken) {
        return Jwts.parser()
                .verifyWith(secretKey).build()
                .parseSignedClaims(getRefreshToken)
                .getPayload();
    }

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

    private String issueRefreshToken(SecretKey secretKey) {
        JWTEntity jwtEntity = JWTEntity.initRefreshToken(SECRET_KEY);
        return Jwts.builder()
                .issuer(jwtEntity.getIssuer())
                .subject(jwtEntity.getSubject())
                .issuedAt(jwtEntity.getIssuedAt())
                .expiration(jwtEntity.getExpiration())
                .signWith(secretKey)
                .compact();
    }

    public Authentication authenticationInfoHelper(Claims claims) {
        String loginId = claims.get("loginId", String.class);
        String authoritiesString = claims.get("authorities", String.class);
        String[] authorities = StringUtils.tokenizeToStringArray(authoritiesString, ",");
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(authorities.length);
        for (String authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
        return new UsernamePasswordAuthenticationToken(loginId, null, grantedAuthorities);
    }

}
