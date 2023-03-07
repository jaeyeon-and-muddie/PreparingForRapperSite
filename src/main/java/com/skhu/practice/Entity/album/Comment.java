package com.skhu.practice.Entity.album;

import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Entity.Base.BaseEntity;
import com.skhu.practice.Entity.User;
import lombok.*;

import javax.persistence.*;


@Getter
@Table(name = "comments")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "album_review_id")
    private AlbumReview albumReview;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 작성자

    public void changeComment(User user, AlbumReview albumReview) {
        this.user=user;
        this.albumReview=albumReview;
    }
}