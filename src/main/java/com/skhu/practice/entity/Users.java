package com.skhu.practice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skhu.practice.dto.ArtistDto;
import com.skhu.practice.dto.UserResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private List<Album> albums;

    public UserResponseDto toResponseDto() {
        return UserResponseDto.builder()
                .id(this.id)
                .email(this.email)
                .username(username)
                .build();
    }

    public ArtistDto toArtistDto() {
        return ArtistDto.builder()
                .id(this.id)
                .email(this.email)
                .username(username)
                .image(this.image)
                .albums(albums.stream()
                        .map(Album::toResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
