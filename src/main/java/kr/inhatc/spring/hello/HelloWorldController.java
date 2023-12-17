package kr.inhatc.spring.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController     // RestAPI Controller - JSON으로 반환
@Slf4j
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/")
    public String hello(Authentication authentication) {
        log.info("authentication: {}", authentication);
        return "Hello World";
    }

    /**
     * 기본 인증 테스트
     * @return Success 문자열 반환
     */
    @GetMapping("/basicauth")
    public String basicAuthCheck() {
        return "Success";
    }

    // /hello-world (endpoint)
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World check!!";
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

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
    }
}
