package kr.inhatc.spring.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

//@Configuration
//@EnableWebSecurity
public class JwtBasicSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(auth -> {
            auth.anyRequest().authenticated();
        });
        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });
        http.httpBasic(Customizer.withDefaults());
        http.csrf(AbstractHttpConfigurer::disable);

        // JWT 설정을 위해 추가
        http.oauth2ResourceServer((oauth2) -> {
            oauth2.jwt(Customizer.withDefaults());
        });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();             // 비밀번호 암호화
    }

    //=================================
    // 1. RSA KeyPair 생성
    //=================================
    @Bean
    public KeyPair keyPair() {                // RSA KeyPair 생성
        try {
            var keyPairGenerator = KeyPairGenerator.getInstance("RSA");  // RSA 암호화 알고리즘
            keyPairGenerator.initialize(2048);                              // 2048bit
            return keyPairGenerator.generateKeyPair();                                 // KeyPair 생성
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    //======================================
    // 2. KeyPair를 이용하여 RSA Key 생성
    //======================================
    @Bean
    public RSAKey rsaKey(KeyPair keyPair) {

        return new RSAKey
                .Builder((RSAPublicKey)keyPair.getPublic())     // RSA 공개키
                .privateKey(keyPair.getPrivate())               // RSA 개인키
                .keyID(UUID.randomUUID().toString())            // Key ID
                .build();
    }

    //==========================================
    // 3. JWKSource 생성(JSON Web Key source)
    //==========================================
    @Bean
    public JWKSource<SecurityContext> jwkSource(RSAKey rsaKey) {
        var jwkSet = new JWKSet(rsaKey);                                // JWKSet 생성
        return (jwkSelector, context) ->  jwkSelector.select(jwkSet);   // JWKSource 생성
    }

    //========================
    // 4. JwtDecoder 생성
    //========================
    @Bean
    public JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException {
        return NimbusJwtDecoder
                .withPublicKey(rsaKey.toRSAPublicKey())     // RSA 공개키를 이용하여 JwtDecoder 생성
                .build();
    }

    //========================
    // 5. JwtEncoder 생성
    //========================
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        return new NimbusJwtEncoder(jwkSource);     // JWKSource를 이용하여 JwtEncoder 생성
    }
}