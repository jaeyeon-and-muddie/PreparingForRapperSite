package com.skhu.practice.DTO.MixtapeDto;

import com.skhu.practice.Entity.User;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MixtapeReviewHomeDto {
    public Long id;
    public String title;
    public int view;
    public double Star;
    public User user;

}
