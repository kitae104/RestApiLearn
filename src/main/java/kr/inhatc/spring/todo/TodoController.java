package kr.inhatc.spring.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/users/{username}/todos")
    public List<TodoDto> retriveTodos(@PathVariable("username") String username){
        log.info("==============> " + username);
        return todoService.findByUsername(username);
    }
}
