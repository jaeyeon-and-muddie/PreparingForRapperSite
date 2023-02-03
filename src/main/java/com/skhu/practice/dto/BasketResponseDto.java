package com.skhu.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketResponseDto {

    private Long id;

    private UserResponseDto purchaser;

    private UserResponseDto seller;

    private ProductResponseDto product;

    private Long buysCount;

    private String createdDate;
}
