package com.skhu.practice.DTO.AlbumDto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentDto {
    public String content;
    public Long albumReviewId;
}
