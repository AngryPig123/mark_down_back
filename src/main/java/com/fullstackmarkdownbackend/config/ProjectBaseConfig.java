package com.fullstackmarkdownbackend.config;

import com.fullstackmarkdownbackend.provider.authentication.BasicUsernamePasswordAuthenticationProvider;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.fullstackmarkdownbackend.constants.ApplicationConstants.CSRF_TOKEN_HEADER;

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

    @Bean
    public AuthenticationManager authenticationManager(
            BasicUsernamePasswordAuthenticationProvider authenticationProvider,
            DefaultAuthenticationEventPublisher defaultAuthenticationEventPublisher
    ) {
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        providerManager.setAuthenticationEventPublisher(defaultAuthenticationEventPublisher);
        return providerManager;
    }

}