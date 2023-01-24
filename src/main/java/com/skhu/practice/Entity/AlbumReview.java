package com.skhu.practice.Entity;

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

    @ElementCollection
    @CollectionTable(name="albumReviews" ,joinColumns=@JoinColumn(name="reviewId"))
    @Column(name="reviews")
    private List<String> reviews;

    @OneToMany(mappedBy = "albumReview", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<Comment> comments;

    @Column(name="TITLE")
    private String title;

    @Column(name="view", columnDefinition = "integer default 0", nullable = false)
    private int view;

    @Column(name="star", nullable=false)
    private double star;
}
