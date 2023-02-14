package com.skhu.practice.DTO.MarketDto;


import com.skhu.practice.Entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MerchandiseDto {
    private Long id;

    private String image;

    private Long point;

    private String name;
}
