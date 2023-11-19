package kr.inhatc.spring.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    // Get /users
    @GetMapping("/users")
    public List<UserDto> retrieveAllUsers() {
        log.info("=================> retrieveAllUsers");
        return userService.findAll();
    }

    // Get /users/{id}
    @GetMapping("/users/{id}")
    public EntityModel<UserDto> retrieveUser(@PathVariable Long id) {  // @PathVariable: URL 경로에 변수를 넣어주는 것
        log.info("=================> retrieveUser");
        UserDto userDto = userService.findOne(id);
        if(userDto == null){
            throw  new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // HATEOAS (Hypermedia As The Engine Of Application State) - REST API에서 링크 정보를 동적으로 생성하는 기법
        EntityModel<UserDto> model = EntityModel.of(userDto);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(link.withRel("all-users"));
        return model;
    }

    // Post /users
    @PostMapping("/users")
    public UserDto createUser(@Valid @RequestBody UserDto userDto) {  // @RequestBody: HTTP 요청 몸체를 자바 객체로 전달받을 수 있음
        log.info("=================> createUser : " + userDto);
        UserDto savedUserDto = userService.save(userDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedUserDto.getId()).toUri();
        log.info("=================> savedUser : " + savedUserDto + ", location : " + location);
        return savedUserDto;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id) {  // @PathVariable: URL 경로에 변수를 넣어주는 것
        log.info("=================> deleteUser");
        userService.deleteById(id);
    }
}
