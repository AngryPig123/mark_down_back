package com.fullstackmarkdownbackend.member.service;

import com.fullstackmarkdownbackend.member.dto.req.MemberJoinRequest;
import com.fullstackmarkdownbackend.member.dto.res.MemberResponse;
import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import com.fullstackmarkdownbackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.fullstackmarkdownbackend.util.validator.CommonValidator.*;

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

    private final MemberRepository memberRepository;

    @Override
    public void insertMember(MemberJoinRequest memberJoinRequest) {
        memberJoinRequest.valid();
        String loginId = memberJoinRequest.getLoginId();
        Optional<MemberEntity> findByMember = memberRepository.findMemberEntityByLoginId(loginId);
        optionalIsPresentException(findByMember, "loginId");
        MemberEntity memberEntity = memberJoinRequest.toEntity();
        memberRepository.save(memberEntity);
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
