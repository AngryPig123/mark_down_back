package com.fullstackmarkdownbackend.member.entity;

import com.fullstackmarkdownbackend.base.entity.BaseEntity;
import com.fullstackmarkdownbackend.base.vo.EncodedType;
import com.fullstackmarkdownbackend.base.vo.Password;
import com.fullstackmarkdownbackend.token.entity.RefreshTokenEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.learnthreading.fullstackmarkdownbackend.member.entity
 * fileName       : UserEntity
 * author         : AngryPig123
 * date           : 24. 9. 1.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 1.        AngryPig123       최초 생성
 */

@Entity
@Getter
@Table(name = "tb_member")
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity {

    /**
     * 회원 가입 시 전달받는 데이터
     */

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login_id", length = 50, nullable = false, unique = true)
    private String loginId;

    @Column(name = "email", length = 60, nullable = false, unique = true)
    private String email;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "lock_count")
    private Integer lockCount;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "first_name", length = 50, nullable = false, unique = true)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false, unique = true)
    private String lastName;

    @Transient
    private final EncodedType passwordEncoderType = EncodedType.BCRYPT;

    @PostPersist
    public void postPersist() {
        this.createdBy = id;
        this.lastModifiedBy = id;
        this.enable = true;
    }

    private MemberEntity(String loginId, String email, String password, String firstName, String lastName) {
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.lockCount = 0;
    }

    public static MemberEntity insertEntity(String loginId, String email, String password, String firstName, String lastName) {
        return new MemberEntity(loginId, email, password, firstName, lastName);
    }

    public void passwordEncoding(Password password) {
        this.password = password.getEncodedPassword();
    }

}
