package com.skhu.practice.DTO;


import com.skhu.practice.Entity.Role;
import com.skhu.practice.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String email;
    private String password;
    private Role role;

    public User toEntity(){
        User user = User.builder()
                .email(email)
                .password(password)
                .role(role.ARTIST)
                .build();
        return user;


    }
}
