package com.skhu.practice.Entity.mixtape;

import com.skhu.practice.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MixtapeReview {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="MIXTAPE_REVIEW_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="mixtapeId")
    private Mixtape mixtape;

    @Column(name="REVIEW")
    private String review;

    @OneToMany(mappedBy="mixtapeReview", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc") // 댓글 정렬
    private List<MixtapeComment> mixtapeComments;

    @Column(name="TITLE")
    private String title;

    @Column(name="view", columnDefinition = "integer default 0", nullable = false)
    private int view;

    @Column(name="star",columnDefinition = "double default 0", nullable=false)
    private double star;

}
