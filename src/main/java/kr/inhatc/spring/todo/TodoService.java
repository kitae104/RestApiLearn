package kr.inhatc.spring.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;

    public List<TodoDto> findByUsername(String username) {
        List<Todo> todoList = todoRepository.findByUsername(username);
        List<TodoDto> todoDtoList = todoList.stream().map(
            todo -> TodoDto.builder()
                    .id(todo.getId())
                    .username(todo.getUsername())
                    .description(todo.getDescription())
                    .targetDate(todo.getTargetDate())
                    .done(todo.getDone())
                    .build())
                .collect(Collectors.toList());
        return todoDtoList;
    }
}
