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
public class MixTapeCommentResponseDto {

    private Long id;

    private UserResponseDto author;

    private MixTapeResponseDto mixTape;

    private String content;

    private Boolean isModified;

    private LocalDate createdDate;

    private List<MixTapeNestedCommentResponseDto> mixTapeNestedComment;
}
