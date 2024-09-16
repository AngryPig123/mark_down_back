package com.fullstackmarkdownbackend.config.serutiry;

import com.fullstackmarkdownbackend.config.PasswordFactory;
import com.fullstackmarkdownbackend.config.serutiry.filter.JWTTokenGeneratorFilter;
import com.fullstackmarkdownbackend.config.serutiry.filter.JWTTokenValidatorFilter;
import com.fullstackmarkdownbackend.config.serutiry.service.MemberDetailsService;
import com.fullstackmarkdownbackend.provider.authentication.BasicUsernamePasswordAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

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

    private final JWTTokenValidatorFilter jWTTokenValidatorFilter;
    private final JWTTokenGeneratorFilter jWTTokenGeneratorFilter;

    @Value("${front.host}")
    private List<String> FRONT_HOSTS;

    @Value("${front.allow-method}")
    private List<String> ALLOW_METHODS;

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
                .anyRequest().authenticated());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        authorizeHttpRequestsConfig(httpSecurity);
        corsConfig(httpSecurity);
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .sessionManagement(sessionConfig -> sessionConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAfter(jWTTokenGeneratorFilter, BasicAuthenticationFilter.class)
                .addFilterBefore(jWTTokenValidatorFilter, BasicAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(MemberDetailsService memberDetailsService, PasswordFactory passwordFactory) {
        BasicUsernamePasswordAuthenticationProvider authenticationProvider =
                new BasicUsernamePasswordAuthenticationProvider(passwordFactory, memberDetailsService);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

}
