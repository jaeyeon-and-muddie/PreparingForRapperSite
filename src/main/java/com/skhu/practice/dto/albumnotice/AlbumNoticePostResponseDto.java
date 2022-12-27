package com.skhu.practice.dto.albumnotice;

import com.skhu.practice.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AlbumNoticePostResponseDto {

    // 검증 로직은 리팩토링 시간에
    private Long postNumber;

    private Long hits;

    private User author;

    private String albumName;

    private String artistName;

    private String content;

    private List<String> songsInAlbum;

    private LocalDate dateOfIssue;

    private LocalDateTime modifiedTime;

    @Builder
    public AlbumNoticePostResponseDto(Long postNumber, Long hits, User author, String albumName, String artistName, String content,
                                 List<String> songsInAlbum, LocalDate dateOfIssue, LocalDateTime modifiedTime) {
        this.postNumber = postNumber;
        this.hits = hits;
        this.author = author;
        this.albumName = albumName;
        this.content = content;
        this.artistName = artistName;
        this.songsInAlbum = songsInAlbum;
        this.dateOfIssue = dateOfIssue;
        this.modifiedTime = modifiedTime;
    }
}
