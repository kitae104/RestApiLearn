package kr.inhatc.spring.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)    // 404 에러를 던짐
public class UserNotFoundException extends RuntimeException {   // RuntimeException을 상속받아서 예외를 만듦
    public UserNotFoundException(String message) {
        super(message);
    }
}