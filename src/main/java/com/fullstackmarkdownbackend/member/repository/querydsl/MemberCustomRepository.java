package com.fullstackmarkdownbackend.member.repository.querydsl;

import com.fullstackmarkdownbackend.member.entity.MemberEntity;

import java.util.List;

/**
 * packageName    : com.fullstackmarkdownbackend.member.repository
 * fileName       : MemberCustomRepository
 * author         : AngryPig123
 * date           : 24. 9. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 2.        AngryPig123       최초 생성
 */
public interface MemberCustomRepository {
    List<MemberEntity> findAllByName(String name);
}
