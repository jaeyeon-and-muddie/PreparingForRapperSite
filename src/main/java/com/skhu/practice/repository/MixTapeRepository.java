package com.skhu.practice.repository;

import com.skhu.practice.entity.MixTape;
import com.skhu.practice.entity.MixTapeComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MixTapeRepository extends JpaRepository<MixTape, Long> {

    List<MixTape> findTop5ByNumberOfReviewGreaterThanEqualOrderByAverageOfStarDesc(Long numberOfReview);
}
