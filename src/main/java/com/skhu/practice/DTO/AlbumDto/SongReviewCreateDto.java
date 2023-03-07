package com.skhu.practice.DTO.AlbumDto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongReviewCreateDto {
    Long albumReviewId;

    Long songId;

    String review;


}
