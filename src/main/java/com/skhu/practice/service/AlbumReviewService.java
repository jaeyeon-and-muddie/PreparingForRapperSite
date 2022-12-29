package com.skhu.practice.service;

import com.skhu.practice.dto.AlbumReviewRequestDto;
import com.skhu.practice.dto.AlbumReviewResponseDto;
import com.skhu.practice.entity.Album;
import com.skhu.practice.entity.AlbumReview;
import com.skhu.practice.repository.AlbumRepository;
import com.skhu.practice.repository.AlbumReviewRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumReviewService {

    private final AlbumRepository albumRepository;
    private final AlbumReviewRepository albumReviewRepository;
    private final UserRepository userRepository;

    public List<AlbumReviewResponseDto> findAllReviewByAlbum(Long albumId) {
        return albumReviewRepository.findByAlbumId(albumId)
                .stream()
                .map(AlbumReview::toResponseDto)
                .collect(Collectors.toList());
    }

    public void saveAlbumReview(Long albumId, AlbumReviewRequestDto albumReviewRequestDto, String username) {
        Album album = albumRepository.findById(albumId).orElseThrow(NoSuchElementException::new);
        album.reviewForThisAlbum(albumReviewRequestDto.getStar());
        albumRepository.save(album);
        albumReviewRepository.save(AlbumReview.builder()
                .album(album)
                .title(albumReviewRequestDto.getTitle())
                .author(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new))
                .reviewOfSongs(albumReviewRequestDto.getReviewOfSongs())
                .star(albumReviewRequestDto.getStar())
                .build());
    }

    public AlbumReviewResponseDto getDetailReview(Long reviewId) {
        AlbumReview albumReview = albumReviewRepository.findById(reviewId).orElseThrow(NoSuchElementException::new);
        albumReview.visit();
        albumReview = albumReviewRepository.save(albumReview);

        return albumReview.toResponseDto();
    }

    public void update(Long id, String title, List<String> content, Double star) {
        AlbumReview albumReview = albumReviewRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        albumReview.modified(title, content, star); // update 시간 조정, 그리고 만일 앨범에 대한 정보를 수정하였어도, album 이 변경되어야지, 다른게 변경되는 것이 아니다.
        albumReviewRepository.save(albumReview);
    }

    public void delete(Long id) {
        AlbumReview albumReview = albumReviewRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        albumReviewRepository.delete(albumReview);
    }
}
