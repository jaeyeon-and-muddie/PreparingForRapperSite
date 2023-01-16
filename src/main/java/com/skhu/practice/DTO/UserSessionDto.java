package com.skhu.practice.DTO;


import com.skhu.practice.Entity.Role;
import com.skhu.practice.Entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class UserSessionDto implements Serializable {
    private String email;
    private String password;
    private Role role;

    public UserSessionDto(User user){
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}
