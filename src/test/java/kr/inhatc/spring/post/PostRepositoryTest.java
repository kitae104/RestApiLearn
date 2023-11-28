package kr.inhatc.spring.post;

import kr.inhatc.spring.user.User;
import kr.inhatc.spring.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Test
    public void postInsertTest() {
        List<User> users = findAllUsers();
        users.forEach(user -> {
            Post post = Post.builder()
                    .description("테스트 게시글" + user.getId())
                    .user(user)
                    .build();
            postRepository.save(post);
        });

        List<Post> posts = postRepository.findAll();
        assertEquals(6, posts.size());
    }

}