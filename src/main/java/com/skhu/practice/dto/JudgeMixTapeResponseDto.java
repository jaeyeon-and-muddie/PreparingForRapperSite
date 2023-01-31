package com.skhu.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class JudgeMixTapeResponseDto { // 보여주는 정보들은 이것 뿐이니..

    private Long id;

    private String soundCloud;

    private Long recommend;

    private Long unRecommend;
}
