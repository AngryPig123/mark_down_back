package com.fullstackmarkdownbackend.member.repository;

import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.reflect.Member;
import java.util.List;

/**
 * packageName    : com.fullstackmarkdownbackend.member.repository
 * fileName       : MemberRepository
 * author         : AngryPig123
 * date           : 24. 9. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 2.        AngryPig123       최초 생성
 */
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
