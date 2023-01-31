package com.skhu.practice.DTO;


import com.skhu.practice.Entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
@Builder
public class MixtapeDetailDto {
    private Long id;
    private int numberOfSongs;
    private String introduction;
    private String title;
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String preTag;

}
