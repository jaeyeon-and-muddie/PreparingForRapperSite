package com.skhu.practice.DTO.AlbumDto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlusSongDto {
    List<PlusSong> plusSongDtoList;

    String AlbumTitle;
}
