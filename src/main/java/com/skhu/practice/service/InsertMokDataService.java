package com.skhu.practice.service;

import com.skhu.practice.entity.Users;
import com.skhu.practice.repository.AlbumCommentRepository;
import com.skhu.practice.repository.AlbumRepository;
import com.skhu.practice.repository.AlbumReviewRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class InsertMokDataService {

    private final AlbumCommentRepository albumCommentRepository;
    private final AlbumRepository albumRepository;
    private final AlbumReviewRepository albumReviewRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void setMokData() { // 여기서 전부 mok date 를 insert 해줄 것이다.
        usersSave();
        albumSave();
        albumReviewSave();
        albumCommentSave();
    }

    private void usersSave() {
        String username = "admin";
        String emailName = "jakind";
        String platformName = "naver.com";
        String password = passwordEncoder.encode("Sksk5839!");

        userRepository.save(Users.builder()
                .username(username)
                .email(emailName + "@" + platformName)
                .password(password)
                .build());

        for (int addNumber = 1; addNumber < 10; addNumber++) {
            userRepository.save(Users.builder()
                    .username(username + addNumber)
                    .email(emailName + addNumber + "@" + platformName)
                    .password(password)
                    .build());
        }
    }

    private void albumSave() { // ID strategy 는 AutoIncrement 이니까 그것을 이용해서 외래키 설정할 예정

    }

    private void albumReviewSave() {

    }

    private void albumCommentSave() {

    }
}
