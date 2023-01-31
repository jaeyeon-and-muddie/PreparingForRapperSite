package com.skhu.practice.DTO;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SoundCloudDto {

    private Long id;
    private String preTag;

    private int recommendNumber;

}
