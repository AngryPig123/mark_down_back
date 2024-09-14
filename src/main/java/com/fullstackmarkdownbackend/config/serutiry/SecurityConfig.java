package com.fullstackmarkdownbackend.config.serutiry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

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
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${front.host}")
    private List<String> FRONT_HOSTS;

    @Value("${front.allow-method}")
    private List<String> ALLOW_METHODS;

    private void authorizeHttpRequestsConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((request) -> request.anyRequest().permitAll());
    }

    private void corsConfig(HttpSecurity httpSecurity) throws Exception {

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();

        //  default cors configuration
        CorsConfiguration defaultCorsConfiguration = new CorsConfiguration();
        defaultCorsConfiguration.setAllowedOrigins(FRONT_HOSTS);
        defaultCorsConfiguration.setAllowedMethods(ALLOW_METHODS);
        defaultCorsConfiguration.addAllowedHeader("*");
        defaultCorsConfiguration.setAllowCredentials(true);
        defaultCorsConfiguration.setMaxAge(3600L);
        corsConfigurationSource.registerCorsConfiguration("/**", defaultCorsConfiguration);

        httpSecurity.cors(config -> config.configurationSource(corsConfigurationSource));

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        authorizeHttpRequestsConfig(httpSecurity);
        corsConfig(httpSecurity);

        httpSecurity.csrf(AbstractHttpConfigurer::disable);

        return httpSecurity.build();
    }

}
