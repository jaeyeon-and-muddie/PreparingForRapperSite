package com.skhu.practice.DTO;

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
