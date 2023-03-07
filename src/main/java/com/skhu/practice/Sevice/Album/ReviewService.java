package com.skhu.practice.Sevice.Album;

import com.skhu.practice.DTO.AlbumDto.CommentDto;
import com.skhu.practice.DTO.AlbumDto.ReviewCreateDto;
import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Entity.*;
import com.skhu.practice.Entity.album.Album;
import com.skhu.practice.Entity.album.AlbumReview;
import com.skhu.practice.Entity.album.Comment;
import com.skhu.practice.Repository.Album.AlbumRepository;
import com.skhu.practice.Repository.Album.AlbumReviewRepository;
import com.skhu.practice.Repository.Album.CommentRepository;
import com.skhu.practice.Repository.Album.ReviewRepository;
import com.skhu.practice.Repository.UserRepository;
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
    private final UserRepository userRepository;


    @Transactional
    public int updateView(Long id) {
        return albumReviewRepository.updateView(id);
    }
    public List<AlbumReview> albumReview(Long albumId){
        List<AlbumReview> albumReviews = reviewRepository.findAllByalbumId(albumId);

        return albumReviews;
    }
    public void albumReviewCreate(ReviewCreateDto reviewCreateDto, User user){
        Album album = albumRepository.findById(reviewCreateDto.getAlbumId()).orElse(null);
        AlbumReview albumReview = AlbumReview.builder()
                .album(album)
                .user(user)
                .star(reviewCreateDto.getStar())
                .title(reviewCreateDto.getTitle())
                .reviews(reviewCreateDto.getReviews())
                .build();
        albumReviewRepository.save(albumReview);
    }
    public void reviewComment(CommentDto commented, UserSessionDto user){
        AlbumReview albumReview = albumReviewRepository.findById(commented.getAlbumReviewId()).orElse(null);
        User user1 = userRepository.findByEmail(user.getEmail()).orElse(null);
        System.out.println(commented);
        Comment comment = Comment.builder()
                        .content(commented.getContent())
                        .albumReview(albumReview)
                        .user(user1)
                        .build();
        commentRepository.save(comment);
    }

    public AlbumReview findReview(Long albumReviewId) {
        AlbumReview albumReview= albumReviewRepository.findById(albumReviewId).orElse(null);
        return albumReview;
    }

}
