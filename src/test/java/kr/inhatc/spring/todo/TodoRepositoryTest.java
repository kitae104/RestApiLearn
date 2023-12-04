package kr.inhatc.spring.todo;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    private void makeTodoList(){
        for (int i = 0; i < 3; i++) {
            Todo todo = Todo.builder()
                    .username("kitae")
                    .description("Get AWS Certified" + i)
                    .done(false)
                    .targetDate(LocalDateTime.now().plusYears(10))
                    .build();
            todoRepository.save(todo);
        }
    }

    @Test
    public void findByUsername() {
        this.makeTodoList();
        List<Todo> todoList = todoRepository.findByUsername("kitae");
        System.out.println(todoList);
    }
}