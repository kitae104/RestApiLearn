package kr.inhatc.spring.user;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private List<String> names = new ArrayList<>();
    public void createUsers(List<String> names){

        Random rand = new Random();

        for (int i = 0; i <3 ; i++) {
            User user = User.builder()
                    .name(names.get(i))
                    .birthDate(LocalDate.now().minusYears(rand.nextInt(40)+1))
                    .build();
            userRepository.save(user);
        }
    }

    public List<String> nameGenerator(){
        Faker faker = new Faker(new Locale("ko-KR"));

        for (int i = 0; i < 3; i++) {
            String name = faker.name().fullName().replace(" ", ""); // 한글 이름 생성
            names.add(name);
        }
        return names;
    }

    @Test
    public void makeUsersTest(){
        List<String> names = nameGenerator();       // 이름 생성
        createUsers(names);                         // 이름으로 유저 생성
        List<User> users = userRepository.findAll();   // 유저 전체 조회
        assertEquals(3, users.size());                 // 유저 3명인지 확인
    }
}