package com.fullstackmarkdownbackend.config.serutiry;

import com.fullstackmarkdownbackend.config.serutiry.exceptionhandling.ProjectAccessDeniedHandler;
import com.fullstackmarkdownbackend.config.serutiry.exceptionhandling.ProjectAuthenticationEntryPoint;
import com.fullstackmarkdownbackend.config.serutiry.filter.JWTTokenGeneratorFilter;
import com.fullstackmarkdownbackend.config.serutiry.filter.JWTTokenValidatorFilter;
import com.fullstackmarkdownbackend.provider.authentication.BasicUsernamePasswordAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
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
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JWTTokenValidatorFilter jWTTokenValidatorFilter;
    private final JWTTokenGeneratorFilter jWTTokenGeneratorFilter;

    private final ProjectAuthenticationEntryPoint projectAuthenticationEntryPoint;
    private final ProjectAccessDeniedHandler projectAccessDeniedHandler;

    @Value("${front.host}")
    private List<String> FRONT_HOSTS;

    @Value("${front.allow-method}")
    private List<String> ALLOW_METHODS;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        authorizeHttpRequestsConfig(httpSecurity);
        corsConfig(httpSecurity);
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jWTTokenValidatorFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jWTTokenGeneratorFilter, BasicAuthenticationFilter.class)
                .exceptionHandling(exceptionHandlingConfig -> exceptionHandlingConfig
                        .authenticationEntryPoint(projectAuthenticationEntryPoint)
                        .accessDeniedHandler(projectAccessDeniedHandler)
                );
        return httpSecurity.build();
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

    private void corsConfig(HttpSecurity httpSecurity) throws Exception {
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(FRONT_HOSTS);
        corsConfiguration.setAllowedMethods(ALLOW_METHODS);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600L);
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        httpSecurity.cors(config -> config.configurationSource(corsConfigurationSource));
    }

    private void authorizeHttpRequestsConfig(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((request) -> request
                .requestMatchers("/api/v1/login").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
        );
    }

}
