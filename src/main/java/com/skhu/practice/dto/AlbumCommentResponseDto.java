package com.skhu.practice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
public class AlbumCommentResponseDto {

    private Long id;

    private UserResponseDto author;

    private AlbumResponseDto album;

    private String content;

    private Boolean isModified;

    private LocalDate createdDate;
}
