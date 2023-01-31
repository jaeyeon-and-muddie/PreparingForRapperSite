package com.skhu.practice.DTO;

import com.skhu.practice.Entity.User;
import com.skhu.practice.Entity.mixtape.Mixtape;
import com.skhu.practice.Entity.mixtape.MixtapeComment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MixtapeReviewDetailDto {
    private long id;

    private User user;

    private String review;

    private List<MixtapeComment> mixtapeComments;

    private String title;

    private int view;

    private double star;
}
