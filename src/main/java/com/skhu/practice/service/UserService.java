package com.skhu.practice.service;

import com.skhu.practice.dto.UserLoginDto;
import com.skhu.practice.entity.User;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User login(UserLoginDto userLoginDto) {
        return userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
                .orElse(null);
    }
}
