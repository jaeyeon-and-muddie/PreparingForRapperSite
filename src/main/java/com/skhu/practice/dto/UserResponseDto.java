package com.skhu.practice.dto;

import com.skhu.practice.dto.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private Long id;

    private String email;

    private String username;

    private Role role;

    private Long point;

    private List<VisitedResponseDto> visited;
}
