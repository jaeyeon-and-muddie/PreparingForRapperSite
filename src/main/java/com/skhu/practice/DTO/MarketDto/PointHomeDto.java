package com.skhu.practice.DTO.MarketDto;


import com.skhu.practice.Entity.User;
import com.skhu.practice.Entity.market.Point;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PointHomeDto {
    private User user;
    private Point havingPoint;

}
