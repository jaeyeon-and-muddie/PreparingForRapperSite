package com.skhu.practice.entity;

import com.skhu.practice.dto.UserResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "USERS") // Table = User 로 설정
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME", unique = true)
    private String username;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Builder
    public Users(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UserResponseDto toResponseDto() {
        return UserResponseDto.builder()
                .id(this.id)
                .email(this.email)
                .build();
    }
}
