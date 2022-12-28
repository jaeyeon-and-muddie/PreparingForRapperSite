package com.skhu.practice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Builder
public class AlbumReviewResponseDto {

    private Long id;

    private AlbumResponseDto album;

    private List<String> reviewOfSongs; // review 의 순서는 동일하다.

    private Double star;

    private UserResponseDto author;

    private LocalDate createdDate;

    private LocalDateTime updateTime;

    private Long hits;

    private String title;
}
