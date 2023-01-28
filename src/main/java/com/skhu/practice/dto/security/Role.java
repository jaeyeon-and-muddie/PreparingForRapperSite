package com.skhu.practice.dto.security;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    ARTIST("ROLE_ARTIST");

    private final String privilege;

    Role(String privilege) {
        this.privilege = privilege;
    }
}
