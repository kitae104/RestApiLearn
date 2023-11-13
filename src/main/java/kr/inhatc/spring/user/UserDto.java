package kr.inhatc.spring.user;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;                // id


    private String name;            // 이름

    private LocalDate birthDate;    // 생일
}
