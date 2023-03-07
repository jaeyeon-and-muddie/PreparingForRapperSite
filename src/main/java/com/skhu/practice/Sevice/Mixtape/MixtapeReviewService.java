package com.skhu.practice.Sevice.Mixtape;

import com.skhu.practice.DTO.*;
import com.skhu.practice.DTO.MixtapeDto.MixtapeReviewCreateDto;
import com.skhu.practice.DTO.MixtapeDto.MixtapeReviewDetailDto;
import com.skhu.practice.DTO.MixtapeDto.MixtapeReviewDto;
import com.skhu.practice.DTO.MixtapeDto.MixtapeReviewHomeDto;
import com.skhu.practice.Entity.mixtape.Mixtape;
import com.skhu.practice.Entity.mixtape.MixtapeComment;
import com.skhu.practice.Repository.Mixtape.MixtapeCommentRepository;
import com.skhu.practice.Entity.mixtape.MixtapeReview;
import com.skhu.practice.Repository.Mixtape.MixtapeRepository;
import com.skhu.practice.Repository.Mixtape.MixtapeReviewRepository;
import com.skhu.practice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MixtapeReviewService {
    private final MixtapeReviewRepository mixtapeReviewRepository;
    private final MixtapeCommentRepository mixtapeCommentRepository;
    private final UserRepository userRepository;
    private final MixtapeRepository mixtapeRepository;

    @Transactional
    public int updateView(Long id) {
        return mixtapeReviewRepository.updateView(id);
    }
    public MixtapeReviewDto reviewHome(Long mixtapeId){
        List<MixtapeReviewHomeDto> mixtapeReviewHomeDtoList = new ArrayList<>();
        List<MixtapeReview> mixtapeReviews= mixtapeReviewRepository.findAllByMixtapeId(mixtapeId);
        for(int i=0; i<mixtapeReviews.size(); i++){
            MixtapeReviewHomeDto mixtapeReviewHomeDto = MixtapeReviewHomeDto.builder()
                    .id(mixtapeReviews.get(i).getId())
                    .title(mixtapeReviews.get(i).getTitle())
                    .Star(mixtapeReviews.get(i).getStar())
                    .user(mixtapeReviews.get(i).getUser())
                    .view(mixtapeReviews.get(i).getView())
                    .build();
            mixtapeReviewHomeDtoList.add(mixtapeReviewHomeDto);
        }
        MixtapeReviewDto mixtapeReviewDto = MixtapeReviewDto.builder()
                .mixtapeReviewHomeDtoList(mixtapeReviewHomeDtoList)
                .id(mixtapeId)
                .build();
        return mixtapeReviewDto;
    }

    public MixtapeReviewDetailDto reviewDetail(Long mixtapeReviewId) {
        MixtapeReview mixtapeReview = mixtapeReviewRepository.findById(mixtapeReviewId).orElse(null);
        MixtapeReviewDetailDto mixtapeReviewDetailDto = MixtapeReviewDetailDto.builder()
                .review(mixtapeReview.getReview())
                .title(mixtapeReview.getTitle())
                .view(mixtapeReview.getView())
                .user(mixtapeReview.getUser())
                .id(mixtapeReview.getId())
                .star(mixtapeReview.getStar())
                .mixtapeComments(mixtapeReview.getMixtapeComments())
                .build();
        return mixtapeReviewDetailDto;
    }

    public void mixtapeReviewComment(MixtapeComment commented) {
        mixtapeCommentRepository.save(commented);
    }

    public void reviewCreate(MixtapeReviewCreateDto create, UserSessionDto user) {
        Mixtape mixtape = mixtapeRepository.findById(create.getMixtapeId()).orElse(null);
        System.out.println(create.getMixtapeId());
        MixtapeReview mixtapeReview = MixtapeReview.builder()
                .review(create.getReview())
                .star(create.getStar())
                .title(create.getTitle())
                .mixtape(mixtape)
                .user(userRepository.findByEmail(user.getEmail()).orElse(null))
                .build();
        mixtapeReviewRepository.save(mixtapeReview);
    }
}
