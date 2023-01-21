package com.skhu.practice.service;

import com.skhu.practice.dto.AlbumRequestDto;
import com.skhu.practice.dto.AlbumReviewRequestDto;
import com.skhu.practice.dto.SongRequestDto;
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
    private final int USER_ID_RANGE = 9;
    private final Random random = new Random();
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

    private void albumSave() {
        String name = "album";
        String[] songsInAlbum = new String[] {"intro", "classic", "old car"};
        List<SongRequestDto> songs = new ArrayList<>();
        LocalDate dateOfIssue = LocalDate.now();
        String introduction = "정민님의 노래는 개지린다";

        for (int index = 0; index < 3; index++) {
            songs.add(SongRequestDto.builder()
                    .lyric("동그란 맘 속에 피어난 how is the life " + index)
                    .title(songsInAlbum[index])
                    .build());
        }

        for (int addNumber = 1; addNumber <= ALBUM_ID_RANGE; addNumber++) {
            albumService.save(AlbumRequestDto.builder()
                            .name(name + addNumber)
                            .introduction(introduction + addNumber)
                            .dateOfIssue(dateOfIssue.minusDays(random.nextInt(32)))
                            .build(), songs, USERNAME + randomNumber(USER_ID_RANGE));
        }
    }

    private void albumReviewSave() {
        String title = "정민님 개 지리누ㅋㅋ";
        String review = "와 개 지리네";
        List<String> reviewOfSong = List.of(review, review, review);

        for (int addNumber = 1; addNumber <= 30; addNumber++) {
            long albumId = randomNumber(ALBUM_ID_RANGE);
            Album album = albumRepository.findById(albumId).orElseThrow(NoSuchElementException::new);

            for (int visitCount = 0; visitCount < randomNumber(ALBUM_ID_RANGE); visitCount++) {
                album.visit();
            }
            albumRepository.save(album);

            albumReviewService.save(albumId, (AlbumReviewRequestDto.builder()
                    .title(title + addNumber)
                    .reviewOfSongs(new ArrayList<>(reviewOfSong))
                    .star((double) random.nextInt(6))
                    .build()), USERNAME);
        }

    }

    private void albumCommentSave() {
        String content = "개 지리누 ㅋㅋㅋ";

        for (int addNumber = 1; addNumber <= 30; addNumber++) {
            long albumId = randomNumber(ALBUM_ID_RANGE);
            albumCommentService.save(albumId, content + addNumber, USERNAME);
            Album album = albumRepository.findById(albumId).orElseThrow(NoSuchElementException::new);
            album.visit();
            albumRepository.save(album);
        }
    }

    private int randomNumber(int range) {
        return random.nextInt(range) + 1;
    }
}
