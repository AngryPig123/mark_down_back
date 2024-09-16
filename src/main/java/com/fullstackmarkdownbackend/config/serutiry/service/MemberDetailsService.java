package com.fullstackmarkdownbackend.config.serutiry.service;

import com.fullstackmarkdownbackend.config.serutiry.MemberDetails;
import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import com.fullstackmarkdownbackend.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * packageName    : com.fullstackmarkdownbackend.config.serutiry.service
 * fileName       : MemberDetailService
 * author         : AngryPig123
 * date           : 24. 9. 16.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 16.        AngryPig123       최초 생성
 */
@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    /**
     * username ->  basic : loginId,  oauth2 : clientId
     *
     * @param loginId the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String loginId /* username */) throws UsernameNotFoundException {
        MemberEntity member = memberRepository
                .findMemberEntityByLoginId(loginId)
                .orElseThrow(
                        () -> new UsernameNotFoundException("member details not found")
                );
        return new MemberDetails(member);
    }

}
