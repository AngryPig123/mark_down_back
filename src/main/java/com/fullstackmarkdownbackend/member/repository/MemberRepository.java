package com.fullstackmarkdownbackend.member.repository;

import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : com.fullstackmarkdownbackend.member.repository
 * fileName       : MemberRepository
 * author         : AngryPig123
 * date           : 24. 9. 14.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 14.        AngryPig123       최초 생성
 */
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findMemberEntityByLoginId(String loginId);
}
