package com.skhu.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AlbumReviewRequestDto { // 그냥 이렇게 request dto 는 받자

    // Validate 할 것은 추후에 작성하자
    private String artistName;

    private String albumName;

    private String songsInAlbum;

    private LocalDate dateOfIssue;

    private String content;
} // 현재로선 {아티스트 이름, 앨범 이름, 수록곡(, 로 구분 되어 있을 것임), 발매일, 리뷰내용
