package kr.inhatc.spring.security;

import jakarta.annotation.security.RolesAllowed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class WorkResource {

    private static final List<Work> WORKS_LIST = List.of(new Work("kitae", "Learn AWS"),
            new Work("kitae", "Get AWS Certified"));

    @GetMapping("/works")
    public List<Work> retriveAllWorks(){
        return WORKS_LIST;
    }

    /**
     * 시큐리티 설정 연습 1
     * @param username
     * @return
     */
    @GetMapping("/users/{username}/works")
    @PreAuthorize("hasRole('ROLE_USER') and #username == authentication.name")      // 권한과 이름 확인
    @PostAuthorize("returnObject.username == 'kitae'")                              // 리턴 객체의 내용 확인
    @RolesAllowed({"ADMIN", "USER"})                                                // 설정에 @EnableMethodSecurity(jsr250Enabled = true) 사용
    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    public Work retriveWorksForSpecificUser(@PathVariable("username") String username){
        return WORKS_LIST.get(0);
    }

    @PostMapping("/users/{username}/works")
    public void createWorksForSpecificUser(@PathVariable("username") String username,
                                           @RequestBody Work work){

        log.info("Create {} for {}", work, username);

    }
}

record Work(String username, String description) {}