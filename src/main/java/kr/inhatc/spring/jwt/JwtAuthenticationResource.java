package kr.inhatc.spring.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.stream.Collectors;

//@RestController
@RequiredArgsConstructor
public class JwtAuthenticationResource {

    private final JwtEncoder jwtEncoder;

    /**
     * 인증 확인
     * @param authentication
     * @return
     */
    @PostMapping("/authenticate")
    public JwtTokenResponse authenticate(Authentication authentication) {
        return new JwtTokenResponse(createToken(authentication));
    }

    private String createToken(Authentication authentication) {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")                                                    // 발급자
                .issuedAt(Instant.now())                                           // 발급 시간
                .expiresAt(Instant.now().plusSeconds(60 * 30))    // 30분 만료 시간
                .claim("scope", createScope(authentication))               // 권한
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();    // 토큰 생성 반환
    }

    private String createScope(Authentication authentication) {
        return authentication.getAuthorities().stream()         // 권한 목록
                .map(authority -> authority.getAuthority())     // 권한
                .collect(Collectors.joining(" ")); // 구분자
    }
}
