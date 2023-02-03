package com.skhu.practice.service;

import com.skhu.practice.dto.AlbumReviewRequestDto;
import com.skhu.practice.dto.AlbumReviewResponseDto;
import com.skhu.practice.dto.MixTapeReviewRequestDto;
import com.skhu.practice.dto.MixTapeReviewResponseDto;
import com.skhu.practice.entity.Album;
import com.skhu.practice.entity.AlbumReview;
import com.skhu.practice.entity.MixTape;
import com.skhu.practice.entity.MixTapeReview;
import com.skhu.practice.repository.MixTapeRepository;
import com.skhu.practice.repository.MixTapeReviewRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MixTapeReviewService {

    private final MixTapeReviewRepository mixTapeReviewRepository;
    private final MixTapeRepository mixTapeRepository;
    private final UserRepository userRepository;

    public List<MixTapeReviewResponseDto> findAllReviewByAlbum(Long mixTapeId) {
        return mixTapeReviewRepository.findByMixTapeId(mixTapeId)
                .stream()
                .map(MixTapeReview::toResponseDto)
                .collect(Collectors.toList());
    }

    public void save(Long mixTapeId, MixTapeReviewRequestDto mixTapeReviewRequestDto, String username) {
        MixTape mixTape = mixTapeRepository.findById(mixTapeId).orElseThrow(NoSuchElementException::new);
        mixTape.reviewForThisMixTape(mixTapeReviewRequestDto.getStar());
        mixTapeRepository.save(mixTape);
        mixTapeReviewRepository.save(MixTapeReview.builder()
                .mixTape(mixTape)
                .title(mixTapeReviewRequestDto.getTitle())
                .author(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new))
                .reviewOfSong(mixTapeReviewRequestDto.getReviewOfSong())
                .star(mixTapeReviewRequestDto.getStar())
                .build());
    }

    public MixTapeReviewResponseDto getDetailReview(Long reviewId) {
        MixTapeReview mixTapeReview = mixTapeReviewRepository.findById(reviewId).orElseThrow(NoSuchElementException::new);
        mixTapeReview.visit();
        mixTapeReview = mixTapeReviewRepository.save(mixTapeReview);

        return mixTapeReview.toResponseDto();
    }
}
