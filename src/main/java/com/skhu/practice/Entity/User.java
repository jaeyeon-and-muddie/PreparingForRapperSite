package com.skhu.practice.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@ToString
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @Column(name="PASSWORD")
    private String password;
    @Column(name="EMAIL")
    private String email;

    @Column(name="USER_AUTH")
    private String userAuth;

    @OneToMany(mappedBy="user")
    List<AlbumReview> albumReviews;
}
