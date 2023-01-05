package com.skhu.practice.Repository;

import com.skhu.practice.Entity.SongReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongReviewRepository extends JpaRepository<SongReview, Long> {
    List<SongReview> findAllByalbumReviewId(Long albumReviewId);
}
