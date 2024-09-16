package com.fullstackmarkdownbackend.login.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.fullstackmarkdownbackend.login.dto.req
 * fileName       : MemberLoginRequest
 * author         : AngryPig123
 * date           : 24. 9. 15.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 15.        AngryPig123       최초 생성
 */
@Getter
@AllArgsConstructor
public class MemberLoginRequest {
    private String loginId;
    private String password;
}
