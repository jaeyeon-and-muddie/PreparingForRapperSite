package com.skhu.practice.service;

import com.skhu.practice.dto.UserLoginDto;
import com.skhu.practice.entity.User;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final String LOGIN_SUCCESS = "review";
    private final String LOGIN_FAILED = "login";

    public String login(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
                .orElse(null);

        if (user == null) {
            return LOGIN_FAILED;
        }

        return "redirect:" + LOGIN_SUCCESS;
    }
}
