package com.skhu.practice.Sevice;
import com.skhu.practice.DTO.UserLoginDto;
import com.skhu.practice.Entity.User;
import com.skhu.practice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final String LOGIN_SUCCESS = "home";
    private final String LOGIN_FAILED = "login";

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public String login(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
                .orElse(null);

        if (user == null) {
            return LOGIN_FAILED;
        }

        return LOGIN_SUCCESS;
    }
}