package com.fullstackmarkdownbackend.login.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * packageName    : com.fullstackmarkdownbackend.login.dto.res
 * fileName       : MemberJWTResponse
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
public class MemberJWTResponse {
    private String status;
    private String accessToken;
    private String refreshToken;
}
