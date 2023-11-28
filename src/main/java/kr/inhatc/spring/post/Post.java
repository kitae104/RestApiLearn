package kr.inhatc.spring.post;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import kr.inhatc.spring.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;                // id

    @Size(min = 5, message = "내용은 5 글자 이상 입력해 주세요.")
    private String description;     // 게시글 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;            // 게시글 작성자
}
