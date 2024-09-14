package com.fullstackmarkdownbackend.member.dto.req;

import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import lombok.*;

import static com.fullstackmarkdownbackend.util.validator.CommonValidator.*;

/**
 * packageName    : com.fullstackmarkdownbackend.member.dto.res
 * fileName       : MemberJoinRequest
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberJoinRequest {

    private String loginId;
    private String email;
    private String password;
    private String passwordCheck;
    private String firstName;
    private String lastName;

    public MemberJoinRequest(String loginId, String email, String password, String passwordCheck, String firstName, String lastName) {
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public void valid() {
        isNull(this.loginId, "loginId");
        isNull(this.email, "email");
        isNull(this.password, "password");
        isNull(this.passwordCheck, "passwordCheck");
        isNull(this.firstName, "firstName");
        isNull(this.lastName, "lastName");
        notEquals(this.password, this.passwordCheck, "password");
    }

    public MemberEntity toEntity() {
        this.valid();
        return MemberEntity.insertEntity(loginId, email, password, firstName, lastName);
    }

}
