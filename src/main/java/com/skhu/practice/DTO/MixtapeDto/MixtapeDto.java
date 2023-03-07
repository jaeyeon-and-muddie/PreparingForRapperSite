package com.skhu.practice.DTO.MixtapeDto;

import com.skhu.practice.Entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MixtapeDto {
    private Long id;
    private User user;

    private String title;

}
