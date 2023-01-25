package com.skhu.practice.repository;

import com.skhu.practice.entity.MixTapeReview;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MixTapeReviewRepository extends JpaRepository<MixTapeReview, Long> {

    List<MixTapeReview> findByMixTapeId(Long mixTapeId);
}
