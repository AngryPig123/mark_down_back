package com.fullstackmarkdownbackend.login.service;

import com.fullstackmarkdownbackend.login.dto.req.MemberLoginRequest;
import com.fullstackmarkdownbackend.login.dto.res.MemberJWTResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.fullstackmarkdownbackend.login.service
 * fileName       : MemberLoginServiceImpl
 * author         : AngryPig123
 * date           : 24. 9. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 15.        AngryPig123       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberLoginServiceImpl implements MemberLoginService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private Authentication authenticationHelper(Authentication authentication) {
        return authenticationManagerBuilder.getObject().authenticate(authentication);
    }

    private void basicLoginValid(MemberLoginRequest memberLoginRequest) {
        String username = memberLoginRequest.getLoginId();
        String password = memberLoginRequest.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = this.authenticationHelper(token);
        authentication.getPrincipal();
    }

    @Override
    public MemberJWTResponse basicLogin(MemberLoginRequest memberLoginRequest) {


        return null;
    }

}
