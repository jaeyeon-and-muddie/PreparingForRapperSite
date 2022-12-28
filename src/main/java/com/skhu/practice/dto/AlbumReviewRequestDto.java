package com.skhu.practice.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class AlbumReviewRequestDto {

    private String title;

    private List<String> reviewOfSongs;

    private Double star;
}
