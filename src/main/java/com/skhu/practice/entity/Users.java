package com.skhu.practice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skhu.practice.dto.ArtistDto;
import com.skhu.practice.dto.UserResponseDto;
import com.skhu.practice.dto.security.Role;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
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

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    @ToString.Exclude
    private List<Visited> visited;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "point")
    private Long point;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    @ToString.Exclude
    private List<Album> albums;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    @ToString.Exclude
    private List<MixTape> mixTapes;

    @OneToMany(mappedBy = "purchaser")
    @JsonIgnore
    @ToString.Exclude
    private List<Basket> myBasket;

    @OneToMany(mappedBy = "seller")
    @JsonIgnore
    @ToString.Exclude
    private List<Sales> mySales;

    @OneToMany(mappedBy = "purchaser")
    @JsonIgnore
    @ToString.Exclude
    private List<Purchases> myPurchases;

    @PrePersist
    private void prePersist() {
        if (image == null) {
            image = "https://www.shutterstock.com/image-vector/profile-picture-avatar-icon-vector-260nw-1760295569.jpg";
        }
        if (point == null ){
            point = 0L;
        }
    }

    public void paymentPoint(Long point) {
        this.point += point;
    }

    public void usePoint(Long usePoint) {
        this.point -= usePoint;
    }

    public void saleProduct(Long price) {
        this.point += price;
    }

    public boolean isPossibleToBuy(Long usePoint) {
        return this.point - usePoint >= 0;
    }

    public UserResponseDto toResponseDto() {
        return UserResponseDto.builder()
                .id(this.id)
                .email(this.email)
                .username(this.username)
                .role(this.role)
                .point(this.point)
                .visited(this.visited.stream()
                        .map(Visited::toResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }


    public ArtistDto toArtistDto() {
        return ArtistDto.builder()
                .id(this.id)
                .email(this.email)
                .username(username)
                .image(this.image)
                .albums(this.albums.stream()
                        .map(Album::toResponseDto)
                        .collect(Collectors.toList()))
                .mixTapes(this.mixTapes.stream()
                        .map(MixTape::toResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
