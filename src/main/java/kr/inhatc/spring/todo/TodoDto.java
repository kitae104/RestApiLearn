package kr.inhatc.spring.todo;

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

    private LocalDateTime targetDate;

    private Boolean done;
}
