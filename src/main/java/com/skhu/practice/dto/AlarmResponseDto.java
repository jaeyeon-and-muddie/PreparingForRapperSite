package com.skhu.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AlarmResponseDto {

    private Long id; // id 가 필요한 이유는, 사용자가 조회했을 때 boolean 을 바꿔야함

    private String message;

    private Boolean isUserChecked;

    private LocalDateTime alarmCreatedTime;
}
