package com.fullstackmarkdownbackend.provider.authentication;

import com.fullstackmarkdownbackend.base.vo.EncodedType;
import com.fullstackmarkdownbackend.config.PasswordFactory;
import com.fullstackmarkdownbackend.config.serutiry.MemberDetails;
import com.fullstackmarkdownbackend.config.serutiry.service.MemberDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.fullstackmarkdownbackend.provider
 * fileName       : BasicAuthenticationProvider
 * author         : AngryPig123
 * date           : 24. 9. 16.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 16.        AngryPig123       최초 생성
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BasicUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final PasswordFactory passwordFactory;
    private final MemberDetailsService memberDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        MemberDetails memberDetails = (MemberDetails) memberDetailsService.loadUserByUsername(username);
        EncodedType encodedType = memberDetails.encodedType();
        PasswordEncoder passwordEncoder = passwordFactory.passwordEncoderFactory(encodedType);
        if (passwordEncoder.matches(password, memberDetails.getPassword())) {
            return new UsernamePasswordAuthenticationToken(username, password, memberDetails.getAuthorities());
        } else {
            throw new BadCredentialsException("invalid password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
