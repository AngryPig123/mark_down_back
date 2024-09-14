package com.fullstackmarkdownbackend.member.controller;

import com.fullstackmarkdownbackend.member.dto.req.MemberJoinRequest;
import com.fullstackmarkdownbackend.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.fullstackmarkdownbackend.version.VersionConstants.VERSION;

/**
 * packageName    : com.fullstackmarkdownbackend.member.controller
 * fileName       : MemberJoinController
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = VERSION + "/member")
public class MemberJoinController {

    private final MemberService memberService;

    @PostMapping(path = "/join")
    public ResponseEntity<String> memberJoinTestController(
            @RequestBody MemberJoinRequest memberJoinRequest
    ) {
        memberService.insertMember(memberJoinRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
