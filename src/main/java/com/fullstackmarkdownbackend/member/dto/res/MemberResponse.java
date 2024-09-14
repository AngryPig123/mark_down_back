package com.fullstackmarkdownbackend.member.dto.res;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


/**
 * packageName    : com.fullstackmarkdownbackend.member.dto.res
 * fileName       : MemberResponse
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
public class MemberResponse {

    private final String email;
    private final String loginId;
    private final String firstName;
    private final String lastName;
    private final String fullName;

    public MemberResponse(String email, String loginId, String firstName, String lastName) {
        this.email = email;
        this.loginId = loginId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
    }

}
