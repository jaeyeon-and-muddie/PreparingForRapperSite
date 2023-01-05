package com.skhu.practice.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class SongReview {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="SONG_REVIEW_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "albumReviewId")
    AlbumReview albumReview;

    @ManyToOne
    @JoinColumn(name="songId")
    Song song;

    @Column(name="REVIEW", columnDefinition = "LONGTEXT")
    private String review;


}
