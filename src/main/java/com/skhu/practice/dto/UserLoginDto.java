package com.skhu.practice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class UserLoginDto {

    private String email;
    private String password; // 이 두가지 정보를 로그인 창에서 받아올 것임

    public void validate() { // 근데, 이거는 Global Handler Exception 을 작성해야지 할 수 있음, 조금만 추후에하자
        // 검사를 진행하는 메소드 아직 작성 x, 이메일 폼을 검사할 필요가 없는데.. 이미 input 에서 검사해줘버림
    }
}
