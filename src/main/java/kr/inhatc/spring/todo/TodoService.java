package kr.inhatc.spring.todo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;

    private final ModelMapper modelMapper;

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

    public TodoDto findById(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        TodoDto todoDto = modelMapper.map(todo, TodoDto.class);
        return todoDto;
    }

    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }

    public void updateTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        todoRepository.save(todo);
    }

    public TodoDto addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = Todo.builder()
                .username(username)
                .description(description)
                .targetDate(targetDate)
                .done(done)
                .build();
        Todo savedTodo = todoRepository.save(todo);
        TodoDto todoDto = modelMapper.map(savedTodo, TodoDto.class);
        return todoDto;
    }
}
