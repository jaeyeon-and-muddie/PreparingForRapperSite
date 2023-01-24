package com.skhu.practice.Sevice;

import com.skhu.practice.DTO.ReviewCreateDto;
import com.skhu.practice.Entity.*;
import com.skhu.practice.Repository.AlbumRepository;
import com.skhu.practice.Repository.AlbumReviewRepository;
import com.skhu.practice.Repository.CommentRepository;
import com.skhu.practice.Repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final AlbumRepository albumRepository;
    private final AlbumReviewRepository albumReviewRepository;
    private final CommentRepository commentRepository;


    @Transactional
    public int updateView(Long id) {
        return albumReviewRepository.updateView(id);
    }
    public List<AlbumReview> albumReview(Long albumId){
        List<AlbumReview> albumReviews = reviewRepository.findAllByalbumId(albumId);

        return albumReviews;
    }
    public void albumReviewCreate(ReviewCreateDto reviewCreateDto, User user){
        AlbumReview albumReview = new AlbumReview();
        Album album = albumRepository.findById(reviewCreateDto.getAlbumId()).orElse(null);
        albumReview.setAlbum(album);
        albumReview.setUser(user);
        albumReview.setStar(reviewCreateDto.getStar());
        albumReview.setTitle(reviewCreateDto.getTitle());
        albumReview.setReviews(reviewCreateDto.getReviews());
        albumReviewRepository.save(albumReview);
    }
    public void reviewComment(Comment comment){
        commentRepository.save(comment);
    }

}
