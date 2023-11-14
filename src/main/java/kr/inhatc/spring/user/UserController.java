package kr.inhatc.spring.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
    public UserDto retrieveUser(@PathVariable Long id) {  // @PathVariable: URL 경로에 변수를 넣어주는 것
        log.info("=================> retrieveUser");
        UserDto userDto = userService.findOne(id);
        if(userDto == null){
            throw  new UserNotFoundException(String.format("ID[%s] not found", id));
        }
        return userDto;
    }

    // Post /users
    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserDto userDto) {
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
