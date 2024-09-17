package com.fullstackmarkdownbackend.config.serutiry;

import com.fullstackmarkdownbackend.base.vo.EncodedType;
import com.fullstackmarkdownbackend.base.vo.TokenType;
import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import com.fullstackmarkdownbackend.token.dto.res.TokenResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * packageName    : com.fullstackmarkdownbackend.config.serutiry
 * fileName       : MemberDetails
 * author         : AngryPig123
 * date           : 24. 9. 16.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 16.        AngryPig123       최초 생성
 */
@AllArgsConstructor
public class MemberDetails implements UserDetails {

    private final MemberEntity memberEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_MEMBER"));
    }

    @Override
    public String getPassword() {
        return memberEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return memberEntity.getLoginId();
    }

    @Override
    public boolean isEnabled() {
        return memberEntity.getEnable();
    }

    public EncodedType encodedType() {
        return memberEntity.getPasswordEncoderType();
    }

    public TokenResponse getMemberRefreshToken() {
        return new TokenResponse(TokenType.REFRESH_TOKEN, memberEntity.getRefreshTokenEntity().getRefreshToken());
    }

}
