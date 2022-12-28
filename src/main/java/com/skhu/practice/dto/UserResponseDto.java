package com.skhu.practice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class UserResponseDto {

    private Long id;

    private String email; // 이 정도만 알면 됨, 더 알 필요가 없음
}
