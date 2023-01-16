package com.skhu.practice.Repository;

import com.skhu.practice.Entity.AlbumReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumReviewRepository extends JpaRepository<AlbumReview, Long> {
    AlbumReview findByTitle(String title);
}
