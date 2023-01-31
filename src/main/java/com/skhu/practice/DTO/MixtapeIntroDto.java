package com.skhu.practice.DTO;


import com.skhu.practice.Entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
public class MixtapeIntroDto {
    private Long id;
    private String title;
    private int numberOfSongs;
    private String introduction;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ReleaseDate;
    private String preTag;
    private User user;
    private int recommendNumber;

}
