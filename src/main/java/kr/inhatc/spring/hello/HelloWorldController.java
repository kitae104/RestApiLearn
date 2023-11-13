package kr.inhatc.spring.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController     // RestAPI Controller - JSON으로 반환
public class HelloWorldController {

    // /hello-world (endpoint)
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World");       // JSON 형태로 반환
    }

    // Path Parameters
    // /hello-world/path-variable/{name} -> /hello-world/path-variable/kitae
    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }
}
