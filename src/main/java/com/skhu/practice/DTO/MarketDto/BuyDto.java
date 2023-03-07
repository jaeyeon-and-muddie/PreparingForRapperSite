package com.skhu.practice.DTO.MarketDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyDto {
    List<BuyListDto> buyListDtoList;

    Integer sum;
}
