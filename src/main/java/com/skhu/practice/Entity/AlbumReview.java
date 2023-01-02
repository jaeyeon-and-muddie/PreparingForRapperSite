package com.skhu.practice.Entity;

import com.skhu.practice.Entity.SongReview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class AlbumReview {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ALBUM_REVIEW_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="albumId")
    private Album album;
    @JsonIgnore
    @OneToMany(mappedBy="albumReview")
    List<SongReview> songReviews;

    @Column(name="TITLE")
    private String title;
}
