package com.fullstackmarkdownbackend.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * packageName    : com.fullstackmarkdownbackend.config
 * fileName       : ProjectConfig
 * author         : AngryPig123
 * date           : 24. 9. 1.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 1.        AngryPig123       최초 생성
 */

@Configuration
@RequiredArgsConstructor
public class ProjectBaseConfig {

    private final EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

}

