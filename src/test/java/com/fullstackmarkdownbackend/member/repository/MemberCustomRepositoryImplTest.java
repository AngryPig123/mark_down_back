package com.fullstackmarkdownbackend.member.repository;

import com.fullstackmarkdownbackend.config.ProjectBaseConfig;
import com.fullstackmarkdownbackend.config.TestConfig;
import com.fullstackmarkdownbackend.member.entity.MemberEntity;
import com.fullstackmarkdownbackend.member.repository.querydsl.MemberCustomRepository;
import com.fullstackmarkdownbackend.member.repository.querydsl.MemberCustomRepositoryImpl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.fullstackmarkdownbackend.member.repository
 * fileName       : MemberRepositoryImplTest
 * author         : AngryPig123
 * date           : 24. 9. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 2.        AngryPig123       최초 생성
 */

@DataJpaTest
@Import({TestConfig.class, MemberCustomRepositoryImpl.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberCustomRepositoryImplTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberCustomRepository memberCustomRepository;

    @BeforeEach
    void setup() {
        MemberEntity user1 = MemberEntity.init("user1", "홍길동");
        MemberEntity user2 = MemberEntity.init("user2", "노형욱");
        MemberEntity user3 = MemberEntity.init("user3", "토토");
        memberRepository.saveAll(List.of(user1, user2, user3));
    }

    @Test
    void init() {
        List<MemberEntity> findByMemberName = memberCustomRepository.findAllByName("토토");
        Assertions.assertEquals(1, findByMemberName.size());
    }

}