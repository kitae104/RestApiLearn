package kr.inhatc.spring.post;

import jakarta.validation.Valid;
import kr.inhatc.spring.user.User;
import kr.inhatc.spring.user.UserNotFoundException;
import kr.inhatc.spring.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    @GetMapping("/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));
        return user.getPosts();
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable Long id, @Valid @RequestBody Post post){
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id : " + id));
        post.setUser(user);
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        log.info(location.toString());
        return ResponseEntity.created(location).build();
    }
}
