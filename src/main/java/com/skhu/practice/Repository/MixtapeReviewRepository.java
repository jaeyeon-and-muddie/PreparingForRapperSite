package com.skhu.practice.Repository;

import com.skhu.practice.Entity.mixtape.MixtapeReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MixtapeReviewRepository extends JpaRepository<MixtapeReview, Long > {

    @Modifying // INSERT, UPDATE, DELETE쿼리에서 사용되는 어노테이션.
    @Query("update MixtapeReview p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);
    List<MixtapeReview> findAllByMixtapeId(Long mixtapeId);
}
