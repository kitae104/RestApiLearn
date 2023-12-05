package kr.inhatc.spring.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @GetMapping("/users/{username}/todos/{id}")
    public TodoDto retriveTodo(@PathVariable("username") String username, @PathVariable("id") Long id){
        log.info("==============> " + username + ", " + id);
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("username") String username, @PathVariable("id") Long id){
        log.info("==============> " + username + ", " + id);
        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
