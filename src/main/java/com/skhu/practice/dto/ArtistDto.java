package com.skhu.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class ArtistDto extends UserResponseDto {

    private String image;

    private List<AlbumResponseDto> albums;
}
