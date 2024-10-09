package com.fullstackmarkdownbackend.config.security;

import com.fullstackmarkdownbackend.config.security.filter.CsrfCookieFilter;
import com.fullstackmarkdownbackend.config.security.filter.JWTTokenGeneratorFilter;
import com.fullstackmarkdownbackend.config.security.filter.JWTTokenValidatorFilter;
import com.fullstackmarkdownbackend.config.security.handler.ProjectAccessDeniedHandler;
import com.fullstackmarkdownbackend.config.security.handler.ProjectAuthenticationEntryPoint;
import com.fullstackmarkdownbackend.config.security.handler.ProjectCsrfTokenRequestAttributeHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.header.writers.XXssProtectionHeaderWriter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static com.fullstackmarkdownbackend.constants.ApplicationConstants.*;

/**
 * packageName    : com.fullstackmarkdownbackend.config.serutiry
 * fileName       : SecurityConfig
 * author         : AngryPig123
 * date           : 24. 9. 2.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 9. 2.        AngryPig123       최초 생성
 */
@Slf4j
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTTokenValidatorFilter jWTTokenValidatorFilter;
    private final JWTTokenGeneratorFilter jWTTokenGeneratorFilter;

    private final ProjectAuthenticationEntryPoint projectAuthenticationEntryPoint;
    private final ProjectAccessDeniedHandler projectAccessDeniedHandler;

    private final CsrfCookieFilter csrfCookieFilter;
    private final ProjectCsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler;

    @Value("${front.host}")
    private List<String> FRONT_HOSTS;

    @Value("${front.allow-method}")
    private List<String> ALLOW_METHODS;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .headers(headersConfig -> headersConfig
                        .xssProtection(xssConfig -> xssConfig
                                .headerValue(XXssProtectionHeaderWriter.HeaderValue.ENABLED_MODE_BLOCK)
                        )
                )
                .cors(corsConfig -> corsConfig
                        .configurationSource(corsConfigurer())
                )
                .securityContext(contextConfig -> contextConfig
                        .requireExplicitSave(false)
                )
                .csrf(csrfConfig -> csrfConfig
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                        .csrfTokenRequestHandler(csrfTokenRequestAttributeHandler)
                )
                .authorizeHttpRequests(requestConfig -> requestConfig
                                .requestMatchers(LOGIN_API_PATH, JOIN_API_PATH, "/api/v1/security/csrf").permitAll()
//                        .requestMatchers(String.format("%s/**", VERSION)).authenticated()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionConfig -> sessionConfig
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jWTTokenValidatorFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(csrfCookieFilter, BasicAuthenticationFilter.class)
                .exceptionHandling(exceptionHandlingConfig -> exceptionHandlingConfig
                        .authenticationEntryPoint(projectAuthenticationEntryPoint)
                        .accessDeniedHandler(projectAccessDeniedHandler)
                );
        return httpSecurity.build();
    }

    private UrlBasedCorsConfigurationSource corsConfigurer() {
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(FRONT_HOSTS);
        corsConfiguration.setAllowedMethods(ALLOW_METHODS);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsConfigurationSource;
    }

}
