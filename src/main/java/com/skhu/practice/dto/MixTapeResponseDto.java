package com.skhu.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MixTapeResponseDto {

    private Long id;

    private String name;

    private List<MixTapeSongResponseDto> songsInMixTape;

    private LocalDate dateOfIssue;

    private String soundCloud;

    private UserResponseDto artist;

    private Long numberOfReview;

    private String averageOfStar;

    private Long hits;

    private String introduction;

    private String image;
}
