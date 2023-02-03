package com.skhu.practice.repository;

import com.skhu.practice.entity.AlbumReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlbumReviewRepository extends JpaRepository<AlbumReview, Long> {

    List<AlbumReview> findByAlbumId(Long id);
}
