package kr.inhatc.spring.todo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoDto {

    private Long id;

    private String username;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate targetDate;

    private Boolean done;
}
