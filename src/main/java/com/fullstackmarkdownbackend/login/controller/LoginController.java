package com.fullstackmarkdownbackend.login.controller;

import com.fullstackmarkdownbackend.constants.ApplicationConstants;
import com.fullstackmarkdownbackend.login.dto.req.MemberLoginRequest;
import com.fullstackmarkdownbackend.login.dto.res.MemberJWTResponse;
import com.fullstackmarkdownbackend.token.dto.res.TokenResponse;
import com.fullstackmarkdownbackend.token.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

import static com.fullstackmarkdownbackend.version.VersionConstants.VERSION;

/**
 * packageName    : com.fullstackmarkdownbackend.login.controller
 * fileName       : LoginController
 * author         : AngryPig123
 * date           : 24. 9. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 15.        AngryPig123       최초 생성
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = VERSION + "/login")
public class LoginController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<MemberJWTResponse> apiLogin(@RequestBody MemberLoginRequest memberLoginRequest) {

        String accessToken = "";
        String refreshToken = "";
        String loginId = memberLoginRequest.getLoginId();
        String password = memberLoginRequest.getPassword();

        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(
                loginId,
                password
        );

        Authentication authenticationResponse = authenticationManager.authenticate(authentication);
        if (Objects.nonNull(authenticationResponse) && authenticationResponse.isAuthenticated()) {
            TokenResponse accessTokenResponse = tokenService.accessTokenIssue(authenticationResponse);
            TokenResponse refreshReIssueTokenResponse = tokenService.validateAndReissueRefreshToken(loginId);
            accessToken = accessTokenResponse.getTokenValue();
            refreshToken = refreshReIssueTokenResponse.getTokenValue();
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new MemberJWTResponse(HttpStatus.OK.getReasonPhrase(), accessToken, refreshToken));

    }

}
