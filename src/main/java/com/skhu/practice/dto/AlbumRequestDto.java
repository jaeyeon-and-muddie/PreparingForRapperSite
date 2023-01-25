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
