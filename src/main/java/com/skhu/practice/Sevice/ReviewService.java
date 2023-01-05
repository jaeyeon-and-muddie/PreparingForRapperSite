package com.skhu.practice.Sevice;

import com.skhu.practice.Entity.AlbumReview;
import com.skhu.practice.Entity.SongReview;
import com.skhu.practice.Repository.ReviewRepository;
import com.skhu.practice.Repository.SongReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final SongReviewRepository songReviewRepository;

    public List<AlbumReview> albumReview(Long albumId){
        List<AlbumReview> albumReviews = reviewRepository.findAllByalbumId(albumId);

        return albumReviews;
    }
    public List<SongReview> songReview(Long albumReviewId){
        List<SongReview> songReviews = songReviewRepository.findAllByalbumReviewId(albumReviewId);
        return songReviews;
    }

}
