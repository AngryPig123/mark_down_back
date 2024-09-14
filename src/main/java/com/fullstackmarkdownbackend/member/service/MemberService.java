package com.fullstackmarkdownbackend.member.service;

import com.fullstackmarkdownbackend.member.dto.req.MemberJoinRequest;

/**
 * packageName    : com.fullstackmarkdownbackend.member.service
 * fileName       : MemberService
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
public interface MemberService {

    void insertMember(MemberJoinRequest memberJoinRequest);

    void existMemberByLoginId(String loginId);

}
