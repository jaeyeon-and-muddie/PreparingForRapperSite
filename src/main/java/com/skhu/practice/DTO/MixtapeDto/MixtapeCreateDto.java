package com.skhu.practice.DTO.MixtapeDto;

import com.skhu.practice.Entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@Builder
public class MixtapeCreateDto {
    private Integer numberOfSongs;
    private String introduction;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String preTag;
}
