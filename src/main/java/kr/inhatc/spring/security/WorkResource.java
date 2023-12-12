package kr.inhatc.spring.security;

import lombok.extern.slf4j.Slf4j;
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
    
    @GetMapping("/users/{username}/works")
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