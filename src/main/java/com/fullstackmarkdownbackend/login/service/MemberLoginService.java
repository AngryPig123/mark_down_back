package com.fullstackmarkdownbackend.login.service;

import com.fullstackmarkdownbackend.login.dto.req.MemberLoginRequest;
import com.fullstackmarkdownbackend.login.dto.res.MemberJWTResponse;
import com.fullstackmarkdownbackend.member.dto.res.MemberResponse;

/**
 * packageName    : com.fullstackmarkdownbackend.login.service
 * fileName       : MemberLoginService
 * author         : AngryPig123
 * date           : 24. 9. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 15.        AngryPig123       최초 생성
 */
public interface MemberLoginService {
    MemberJWTResponse basicLogin(MemberLoginRequest memberLoginRequest);
}
