package com.skhu.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ProductResponseDto {

    private Long id;

    private Long stock;

    private String name;

    private String explain;

    private Long price;

    private String image;

    private UserResponseDto registrant;

    private LocalDate createdDate;
}
