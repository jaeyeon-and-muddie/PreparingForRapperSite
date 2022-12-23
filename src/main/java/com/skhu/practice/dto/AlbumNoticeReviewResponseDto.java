package com.skhu.practice.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlbumNoticeReviewResponseDto {

    // 검증 로직은 리팩토링 시간에
    private Long postNumber;

    private Long hits;

    private Long numberOfSongsInAlbum;

    private String authorName;

    private String albumName;

    private String artistName;

    private LocalDate dateOfIssue;

    private LocalDate createdDate;

    @Builder
    public AlbumNoticeReviewResponseDto(Long postNumber, Long hits, Long numberOfSongsInAlbum, String authorName, String albumName,
                                        String artistName, LocalDate dateOfIssue, LocalDate createdDate) {
        this.postNumber = postNumber;
        this.hits = hits;
        this.numberOfSongsInAlbum = numberOfSongsInAlbum;
        this.authorName = authorName;
        this.albumName = albumName;
        this.artistName = artistName;
        this.dateOfIssue = dateOfIssue;
        this.createdDate = createdDate;
    }
}
