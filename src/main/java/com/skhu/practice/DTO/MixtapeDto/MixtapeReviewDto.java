package com.skhu.practice.DTO.MixtapeDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class MixtapeReviewDto {
    private Long id;

    List<MixtapeReviewHomeDto> mixtapeReviewHomeDtoList;

}
