package com.skhu.practice.Repository;

import com.skhu.practice.Entity.AlbumReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumReviewRepository extends JpaRepository<AlbumReview, Long> {
    AlbumReview findByTitle(String title);

    @Modifying // INSERT, UPDATE, DELETE쿼리에서 사용되는 어노테이션.
    @Query("update AlbumReview p set p.view = p.view + 1 where p.id = :id")
    int updateView(Long id);

    @Query(value = "SELECT ALBUM_ID FROM ALBUM_REVIEW " +
            "GROUP BY ALBUM_ID ORDER BY COUNT(ALBUM_REVIEW_ID) DESC LIMIT 3", nativeQuery = true)
    List<Long>  getCountByAlbumReviewWithReview();

    @Query(value = "SELECT ALBUM_ID FROM ALBUM_REVIEW "+
            "GROUP BY ALBUM_ID ORDER BY SUM(VIEW) DESC LIMIT 3", nativeQuery = true)
    List<Long>  getSumByAlbumReviewWithView();

    @Query(value = "SELECT ALBUM_ID FROM ALBUM_REVIEW " +
            "GROUP BY ALBUM_ID ORDER BY AVG(STAR) DESC LIMIT 3", nativeQuery = true)
    List<Long>  getAvgByAlbumReviewWithStar();

}
