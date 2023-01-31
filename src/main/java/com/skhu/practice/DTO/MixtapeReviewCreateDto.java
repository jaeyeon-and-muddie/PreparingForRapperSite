package com.skhu.practice.DTO;

import com.skhu.practice.Entity.User;
import com.skhu.practice.Entity.mixtape.Mixtape;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MixtapeReviewCreateDto {
    private User user;

    private Long mixtapeId;

    private String review;

    private String title;

    private double star;
}
