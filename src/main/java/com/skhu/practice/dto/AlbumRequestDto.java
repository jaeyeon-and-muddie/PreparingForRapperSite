package com.skhu.practice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class AlbumRequestDto {

    private String name;

    private String songsInAlbum;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;

    private String artistName;

    private String introduction;

    public boolean isNotIllegal() {
        System.out.println(isNotEmptyAndBlank(this.name));
        System.out.println(isNotEmptyAndBlank(this.songsInAlbum));
        System.out.println(isNotEmptyAndBlank(this.artistName));
        System.out.println(isNotEmptyAndBlank(this.introduction));
        System.out.println(dateOfIssue != null);
        return isNotEmptyAndBlank(this.name) &&
                isNotEmptyAndBlank(this.songsInAlbum) &&
                isNotEmptyAndBlank(this.artistName) &&
                isNotEmptyAndBlank(this.introduction) &&
                dateOfIssue != null;
    }

    private boolean isNotEmptyAndBlank(String checkString) {
        return (checkString != null) && !checkString.isBlank() && !checkString.isEmpty();
    }

    public List<String> extractSongsInAlbum() {
        return Arrays.stream(this.songsInAlbum.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }
}
