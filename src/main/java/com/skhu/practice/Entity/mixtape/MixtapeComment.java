package com.skhu.practice.Entity.mixtape;

import com.skhu.practice.Entity.AlbumReview;
import com.skhu.practice.Entity.Base.BaseEntity;
import com.skhu.practice.Entity.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "mixtapeComments")
@Entity
public class MixtapeComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 댓글 내용

    @ManyToOne
    @JoinColumn(name = "mixtape_review_id")
    private MixtapeReview mixtapeReview;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 작성자
}
