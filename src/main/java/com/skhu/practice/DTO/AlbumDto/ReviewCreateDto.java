package com.skhu.practice.DTO.AlbumDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewCreateDto {
    Long albumId;
    String title;

    double star;

    List<String> reviews;
}
