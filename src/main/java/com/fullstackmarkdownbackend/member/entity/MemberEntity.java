package com.fullstackmarkdownbackend.member.entity;

import com.fullstackmarkdownbackend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

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

    @Column(name = "password", length = 200, nullable = false, unique = true)
    private String password;

    @Column(name = "first_name", length = 50, nullable = false, unique = true)
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false, unique = true)
    private String lastName;

    @PostPersist
    public void postPersist() {
        this.createdBy = id;
        this.lastModifiedBy = id;
    }

    private MemberEntity(String loginId, String email, String password, String firstName, String lastName) {
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static MemberEntity insertEntity(String loginId, String email, String password, String firstName, String lastName) {
        return new MemberEntity(loginId, email, password, firstName, lastName);
    }

}
