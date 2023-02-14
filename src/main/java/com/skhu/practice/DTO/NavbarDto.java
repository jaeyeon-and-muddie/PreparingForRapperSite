package com.skhu.practice.DTO;

import com.skhu.practice.Entity.User;
import com.skhu.practice.Entity.market.Point;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NavbarDto {
    User user;
    Point point;

}
