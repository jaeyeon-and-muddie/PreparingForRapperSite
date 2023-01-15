package com.skhu.practice.repository;

import com.skhu.practice.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findByDateOfIssueBetweenOrderByDateOfIssueAsc(LocalDate firstDateOfIssue, LocalDate secondDateOfIssue);

    List<Album> findTop5ByOrderByNumberOfReviewDesc();

    List<Album> findTop5ByOrderByAverageOfStarDesc();

    List<Album> findTop5ByOrderByHitsDesc();
}
