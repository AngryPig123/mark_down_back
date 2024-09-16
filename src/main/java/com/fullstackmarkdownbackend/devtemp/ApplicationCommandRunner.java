package com.fullstackmarkdownbackend.devtemp;

import com.fullstackmarkdownbackend.member.dto.req.MemberJoinRequest;
import com.fullstackmarkdownbackend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.fullstackmarkdownbackend.devtemp
 * fileName       : ApplicationCommandRunner
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
@Profile(value = "!dev")
public class ApplicationCommandRunner implements CommandLineRunner {

    private final MemberService memberService;

    @Override
    public void run(String... args) throws Exception {

        MemberJoinRequest memberJoinRequest = new MemberJoinRequest(
                "admin", "admin@gmail.com", "1q2w3e4R!@", "1q2w3e4R!@", "no", "hyunguk"
        );
        memberService.insertMember(memberJoinRequest);

    }

}
