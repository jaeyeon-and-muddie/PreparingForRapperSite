package com.skhu.practice.dto;

import lombok.Builder;
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
@Builder
@ToString
public class AlbumRequestDto {

    private String name;

//    private String songsInAlbum; // 여기를 Song 으로 받아야함, SongRequestDto 로 받아야할 듯 이거는 정민님이 했던 방법대로 받아야 할 것 같음, Song 입력 란을 따로 만들어서

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfIssue;

    private String introduction;

    private String image;

    public boolean isNotIllegal() {
        return isNotEmptyAndBlank(this.name) &&
                isNotEmptyAndBlank(this.introduction) &&
                dateOfIssue != null;
    }

    private boolean isNotEmptyAndBlank(String checkString) {
        return (checkString != null) && !checkString.isBlank() && !checkString.isEmpty();
    }
}
