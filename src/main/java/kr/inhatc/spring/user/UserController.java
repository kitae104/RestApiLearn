package kr.inhatc.spring.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    // Get /users
    // retrieveAllUsers
    @GetMapping("/users")
    public List<UserDto> retrieveAllUsers() {

        return "all-users";
    }
}
