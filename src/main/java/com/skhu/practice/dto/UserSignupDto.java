package com.skhu.practice.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class UserSignupDto {

    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자ID 입력은 필수사항입니다.")
    private String username;

    @Email
    @NotEmpty(message = "Email 입력은 필수사항입니다.")
    private String email;

    @NotEmpty(message = "비밀번호 입력은 필수사항입니다.")
    private String password1;

    @NotEmpty(message = "확인 비밀번호 입력은 필수사항이빈다.")
    private String password2;
}
