package com.skhu.practice.DTO.MarketDto;

import com.skhu.practice.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BasketListDto {
    public Long num;
    public Long merchandiseId;
}
