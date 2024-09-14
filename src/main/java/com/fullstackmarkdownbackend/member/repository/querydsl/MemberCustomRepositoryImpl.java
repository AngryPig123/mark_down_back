package com.fullstackmarkdownbackend.member.repository.querydsl;

import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import com.fullstackmarkdownbackend.member.repository.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.fullstackmarkdownbackend.member.entity.QMemberEntity.memberEntity;

/**
 * packageName    : com.fullstackmarkdownbackend.member.repository
 * fileName       : MemberRepositoryImpl
 * author         : AngryPig123
 * date           : 24. 9. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 2.        AngryPig123       최초 생성
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemberEntity> findAllByName(String name) {
        List<MemberEntity> memberEntityList = jpaQueryFactory
                .selectFrom(memberEntity)
                .where(memberEntity.name.like(String.format("%%%s%%", name)))
                .fetch();
        log.info("member entity list = {}", memberEntityList);
        return memberEntityList;
    }

}
