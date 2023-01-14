package com.skhu.practice.service;

import com.skhu.practice.dto.ArtistDto;
import com.skhu.practice.dto.UserSignupDto;
import com.skhu.practice.entity.Users;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean isNotEqualsPassword1AndPassword2(UserSignupDto userSignupDto) {
        return !userSignupDto.getPassword1().equals(userSignupDto.getPassword2());
    }

    public void save(UserSignupDto userSignupDto) {
        userRepository.save(Users.builder()
                .username(userSignupDto.getUsername())
                .email(userSignupDto.getEmail())
                .password(passwordEncoder.encode(userSignupDto.getPassword1()))
                .build());
    }

    public ArtistDto findById(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(NoSuchElementException::new)
                .toArtistDto();
    }
}
