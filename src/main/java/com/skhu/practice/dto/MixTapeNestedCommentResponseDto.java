package com.skhu.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MixTapeNestedCommentResponseDto {

    private Long id;

    private UserResponseDto author;

    private String content;

    private Boolean isModified;

    private LocalDate createdDate;
}
