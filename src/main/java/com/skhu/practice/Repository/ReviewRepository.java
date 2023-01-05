package com.skhu.practice.Repository;

import com.skhu.practice.Entity.AlbumReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<AlbumReview, Long> {
    List<AlbumReview> findAllByalbumId(Long albumId);
}
