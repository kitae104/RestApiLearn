package kr.inhatc.spring.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;                // id
    @Size(min = 2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;            // 이름

    @Past(message = "생일은 과거 날짜만 가능합니다.")
    private LocalDate birthDate;    // 생일

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
