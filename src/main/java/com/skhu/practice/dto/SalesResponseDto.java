package com.skhu.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SalesResponseDto {

    private Long id;

    private UserResponseDto purchaser;

    private ProductResponseDto product;

    private Long buysCount;

    private Long totalPrice;

    private Long remainStock;

    private String buyDate;
}
