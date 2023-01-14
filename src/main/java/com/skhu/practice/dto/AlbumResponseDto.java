package com.skhu.practice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class AlbumResponseDto {

    private Long id;

    private String name;

    private List<String> songsInAlbum;

    private LocalDate dateOfIssue;

    private UserResponseDto artist;

    private Long numberOfReview;

    private String averageOfStar;

    private Long hits;

    private String introduction;
}
