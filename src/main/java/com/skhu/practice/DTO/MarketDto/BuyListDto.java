package com.skhu.practice.DTO.MarketDto;

import com.skhu.practice.Entity.User;
import com.skhu.practice.Entity.market.Merchandise;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BuyListDto {
    Long num;
    Merchandise merchandise;
    User user;

    Long basketId;

}
