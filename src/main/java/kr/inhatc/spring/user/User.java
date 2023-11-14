package kr.inhatc.spring.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;                // id

    private String name;            // 이름

    private LocalDate birthDate;    // 생일

    // 엔티티 리스트를 DTO 리스트로 변환
    public static List<UserDto> toList(List<User> users) {
        return users.stream()
                .map(user -> UserDto.builder()
                    .id(user.getId())
                    .name(user.getName())
                    .birthDate(user.getBirthDate())
                    .build())
                .collect(Collectors.toList());
    }
}
