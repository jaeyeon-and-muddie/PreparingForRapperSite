package com.skhu.practice.Entity;

import com.skhu.practice.Entity.album.AlbumReview;
import com.skhu.practice.Entity.album.Role;
import com.skhu.practice.Entity.market.Point;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="USER_TABLE")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ID")
    private long id;

    @Column(name="PASSWORD")
    private String password;
    @Column(name="EMAIL")
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name="ROLE")
    @ColumnDefault("'ROLE_ARTIST'")
    private Role role;

    @OneToMany(mappedBy="user")
    List<AlbumReview> albumReviews;

    @OneToOne(mappedBy="user")
    private Point point;
}
