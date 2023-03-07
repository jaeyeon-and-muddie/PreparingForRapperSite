package com.skhu.practice.DTO.MarketDto;

import com.skhu.practice.Entity.User;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MerchandiseDetailDto {
    private Long id;
    private Long lefted;

    private String image;

    private Long point;

    private String name;

    private User user;
}
