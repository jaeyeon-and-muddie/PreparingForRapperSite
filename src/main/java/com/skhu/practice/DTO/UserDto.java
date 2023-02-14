package com.skhu.practice.DTO;


import com.skhu.practice.Entity.album.Role;
import com.skhu.practice.Entity.User;
import lombok.*;

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
