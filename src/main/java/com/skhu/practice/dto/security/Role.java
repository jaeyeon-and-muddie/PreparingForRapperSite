package com.skhu.practice.dto.security;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private final String privilege;

    Role(String privilege) {
        this.privilege = privilege;
    }
}
