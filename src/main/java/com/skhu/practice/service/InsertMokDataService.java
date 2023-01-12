package com.skhu.practice.service;

import com.skhu.practice.dto.AlbumRequestDto;
import com.skhu.practice.dto.AlbumReviewRequestDto;
import com.skhu.practice.dto.UserSignupDto;
import com.skhu.practice.entity.Album;
import com.skhu.practice.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class InsertMokDataService {

    private final int ALBUM_ID_RANGE = 20;
    private final String USERNAME = "admin";

    private final AlbumCommentService albumCommentService;
    private final AlbumService albumService;
    private final AlbumRepository albumRepository;
    private final AlbumReviewService albumReviewService;
    private final UserService userService;

    public void setMokData() { // 여기서 전부 mok date 를 insert 해줄 것이다.
        usersSave();
        albumSave();
        albumReviewSave();
        albumCommentSave();
    }

    private void usersSave() {
        String emailName = "jakind";
        String platformName = "naver.com";
        String password = "Sksk5839!";

        userService.save(UserSignupDto.builder()
                .username(USERNAME)
                .email(emailName + "@" + platformName)
                .password1(password)
                .build());

        for (int addNumber = 1; addNumber < 10; addNumber++) {
            userService.save(UserSignupDto.builder()
                    .username(USERNAME + addNumber)
                    .email(emailName + addNumber + "@" + platformName)
                    .password1(password)
                    .build());
        }
    }

    private void albumSave() { // ID strategy 는 AutoIncrement 이니까 그것을 이용해서 외래키 설정할 예정
        // 여기서 이제 album 을 save 해야 하는데, 일단 내가 오늘 할 일들을 차근차근 정해보자.
        String name = "album";
        String songsInAlbum = "intro, classic, old car";
        LocalDate dateOfIssue = LocalDate.now();
        String artistName = "정민";
        String introduction = "정민님의 노래는 개지린다";
        Random random = new Random();

        for (int addNumber = 1; addNumber <= ALBUM_ID_RANGE; addNumber++) {
            albumService.save(AlbumRequestDto.builder()
                            .name(name + addNumber)
                            .songsInAlbum(songsInAlbum + addNumber)
                            .artistName(artistName + addNumber)
                            .introduction(introduction + addNumber)
                            .dateOfIssue(dateOfIssue.minusDays(random.nextInt(32)))
                            .build());
        }
    }

    private void albumReviewSave() {
        String title = "정민님 개 지리누ㅋㅋ";
        String review = "와 개 지리네";
        List<String> reviewOfSong = List.of(review, review, review);
        Random random = new Random();

        for (int addNumber = 1; addNumber <= 30; addNumber++) {
            long albumId = random.nextInt(ALBUM_ID_RANGE) + 1;
            Album album = albumRepository.findById(albumId).orElseThrow(NoSuchElementException::new);

            for (int visitCount = 0; visitCount < random.nextInt(ALBUM_ID_RANGE) + 1; visitCount++) {
                album.visit();
            }
            albumRepository.save(album);

            albumReviewService.saveAlbumReview(albumId, (AlbumReviewRequestDto.builder()
                    .title(title + addNumber)
                    .reviewOfSongs(new ArrayList<>(reviewOfSong))
                    .star((double) random.nextInt(6))
                    .build()), USERNAME);
        }

    }

    private void albumCommentSave() {
        Random random = new Random();
        String content = "개 지리누 ㅋㅋㅋ";

        for (int addNumber = 1; addNumber <= 30; addNumber++) {
            long albumId = random.nextInt(ALBUM_ID_RANGE) + 1;
            albumCommentService.save(albumId, content + addNumber, USERNAME);
            Album album = albumRepository.findById(albumId).orElseThrow(NoSuchElementException::new);
            album.visit();
            albumRepository.save(album);
        }
    }
}
