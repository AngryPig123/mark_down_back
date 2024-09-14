package com.fullstackmarkdownbackend.member.controller;

import com.fullstackmarkdownbackend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.fullstackmarkdownbackend.version.VersionConstants.VERSION;

/**
 * packageName    : com.fullstackmarkdownbackend.member.controller
 * fileName       : MemberValidController
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
@RequestMapping(path = VERSION + "/member/valid")
public class MemberValidController {

    private final MemberService memberService;

    @PostMapping(path = "/loginId")
    public ResponseEntity<String> memberJoinTestController(
            @RequestParam("loginId") String loginId
    ) {
        memberService.existMemberByLoginId(loginId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
