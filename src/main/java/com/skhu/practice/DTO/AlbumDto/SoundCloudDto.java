package com.skhu.practice.DTO.AlbumDto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SoundCloudDto {

    private Long id;
    private String preTag;

    private int recommendNumber;

}
