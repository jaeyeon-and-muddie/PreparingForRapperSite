package com.skhu.practice.DTO;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlusAlbumDto {
    public String introduction;
    public Integer numberOfSongs;

    public String title;

    public String artistName;

    public String image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate releaseDate;



}
