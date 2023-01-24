package com.skhu.practice.Entity;

import com.skhu.practice.Entity.Base.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Optional;


@Data
@Table(name = "comments")
@Entity
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content; // 댓글 내용

//    @Column(name = "created_date")
//    @CreatedDate
//    private LocalDate createdDate;
//
//    @Column(name = "updated_date")
//    @LastModifiedDate
//    private LocalDate updatedDate;

    @ManyToOne
    @JoinColumn(name = "album_review_id")
    private AlbumReview albumReview;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 작성자

}