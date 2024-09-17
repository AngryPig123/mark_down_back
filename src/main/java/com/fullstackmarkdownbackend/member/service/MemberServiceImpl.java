package com.fullstackmarkdownbackend.member.service;

import com.fullstackmarkdownbackend.base.vo.EncodedType;
import com.fullstackmarkdownbackend.base.vo.Password;
import com.fullstackmarkdownbackend.member.dto.req.MemberJoinRequest;
import com.fullstackmarkdownbackend.member.dto.res.MemberResponse;
import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import com.fullstackmarkdownbackend.member.repository.MemberRepository;
import com.fullstackmarkdownbackend.token.dto.res.TokenResponse;
import com.fullstackmarkdownbackend.token.service.TokenService;
import com.fullstackmarkdownbackend.util.encoder.EncoderUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.fullstackmarkdownbackend.util.validator.CommonValidator.optionalIsPresentException;

/**
 * packageName    : com.fullstackmarkdownbackend.member.service
 * fileName       : MemberServiceImpl
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final EncoderUtil encoderUtil;
    private final TokenService tokenService;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void insertMember(MemberJoinRequest memberJoinRequest) {
        memberJoinRequest.valid();
        String loginId = memberJoinRequest.getLoginId();
        Optional<MemberEntity> findByMember = memberRepository.findMemberEntityByLoginId(loginId);
        optionalIsPresentException(findByMember, "loginId");
        MemberEntity memberEntity = memberJoinRequest.toEntity();
        String password = memberEntity.getPassword();
        EncodedType passwordEncoderType = memberEntity.getPasswordEncoderType();
        Password passwordEncryption = encoderUtil.passwordEncryption(passwordEncoderType, password);
        memberEntity.passwordEncoding(passwordEncryption);
        memberRepository.save(memberEntity);
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(
                memberJoinRequest.getLoginId(),
                memberJoinRequest.getPassword()
        );
        TokenResponse tokenResponse = tokenService.refreshTokenIssue(authentication);
        tokenService.insertRefreshToken(memberEntity, tokenResponse.getTokenValue());
    }

    @Override
    public void existMemberByLoginId(String loginId) {
        Optional<MemberEntity> memberEntityByLoginId = memberRepository.findMemberEntityByLoginId(loginId);
        optionalIsPresentException(memberEntityByLoginId, "login id");
    }

    private MemberResponse toMemberResponse(MemberEntity memberEntity) {
        String email = memberEntity.getEmail();
        String loginId = memberEntity.getLoginId();
        String firstName = memberEntity.getFirstName();
        String lastName = memberEntity.getLastName();
        return new MemberResponse(email, loginId, firstName, lastName);
    }

}
