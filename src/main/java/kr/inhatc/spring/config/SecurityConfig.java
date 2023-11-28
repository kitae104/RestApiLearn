package kr.inhatc.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 1. 모든 요청에 대해 인증을 요구하도록 설정
        http.authorizeRequests(authorizeRequests ->
                authorizeRequests.anyRequest().authenticated()
        );

        // 2. 만약 인증되지 않은 사용자라면, 로그인 페이지로 이동
        http.httpBasic(withDefaults());

        // 3. CSRF 토큰을 사용하지 않도록 설정
        http.csrf(httpSecurityCsrfConfigurer -> {
            httpSecurityCsrfConfigurer.disable();
        });

        return http.build();
    }
}
