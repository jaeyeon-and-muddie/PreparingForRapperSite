package com.skhu.practice.dto.albumnotice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AlbumNoticeRequestDto { // 그냥 이렇게 request dto 는 받자

    // Validate 할 것은 추후에 작성하자
    private String artistName;

    private String albumName;

    private String songsInAlbum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;

    private String content;

    public Set<String> wrapSongsInAlbum() {
        return Arrays.stream(songsInAlbum.split(","))
                .collect(Collectors.toSet());
    }
} // 현재로선 {아티스트 이름, 앨범 이름, 수록곡(, 로 구분 되어 있을 것임), 발매일, 리뷰내용
