package com.fullstackmarkdownbackend.token.entity;

import com.fullstackmarkdownbackend.base.entity.BaseEntity;
import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * packageName    : com.fullstackmarkdownbackend.token.entity
 * fileName       : RefreshTokenEntity
 * author         : AngryPig123
 * date           : 24. 9. 17.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 17.        AngryPig123       최초 생성
 */
@Entity
@Getter
@Table(name = "tb_refresh_token")
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshTokenEntity extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")  // 외래 키 칼럼 정의
    private MemberEntity memberEntity;

    @Column(name = "refresh_token")
    private String refreshToken;

    @PostPersist
    public void postPersist() {
        this.createdBy = id;
        this.lastModifiedBy = id;
    }

    private RefreshTokenEntity(MemberEntity memberEntity, String refreshToken) {
        this.memberEntity = memberEntity;
        this.refreshToken = refreshToken;
    }

    public static RefreshTokenEntity insertInit(MemberEntity memberEntity, String refreshToken) {
        return new RefreshTokenEntity(memberEntity, refreshToken);
    }

    public void refreshTokenUpdate(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
