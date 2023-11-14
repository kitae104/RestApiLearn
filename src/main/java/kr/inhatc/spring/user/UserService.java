package kr.inhatc.spring.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;              // ModelMapper 객체를 주입받음
    public List<UserDto> findAll() {
        List<User> users = userRepository.findAll();
        log.info("=================> users.size() : " + users.size());
        return User.toList(users);
    }

    public UserDto findOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Not found id" + id));
        log.info("=================> user : " + user);
        UserDto userDto = modelMapper.map(user, UserDto.class);     // ModelMapper를 이용하여 UserDto 객체로 변환
        return userDto;
    }

    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);           // ModelMapper를 이용하여 User 객체로 변환
        User savedUser = userRepository.save(user);
        UserDto savedUserDto = modelMapper.map(savedUser, UserDto.class);   // ModelMapper를 이용하여 UserDto 객체로 변환
        return savedUserDto;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
