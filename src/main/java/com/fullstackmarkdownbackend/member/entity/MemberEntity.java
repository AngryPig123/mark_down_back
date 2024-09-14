package com.fullstackmarkdownbackend.member.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
public class MemberEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "name")
    private String name;

    private MemberEntity(String loginId, String name) {
        this.loginId = loginId;
        this.name = name;
    }

    public static MemberEntity init(String loginId, String name) {
        return new MemberEntity(loginId, name);
    }

    /*
        @Column(name = "nick_name")
        private String nickName;

        @Column(name = "password")
        private String password;

        @Column(name = "birth_date")
        private LocalDate birthDate;
    */

}
