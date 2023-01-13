package com.skhu.practice.controller;

import java.security.Principal;
import java.util.List;

import com.skhu.practice.dto.AlbumReviewRequestDto;

import com.skhu.practice.service.AlbumReviewService;
import com.skhu.practice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/album/review")
@RequiredArgsConstructor
public class AlbumReviewController {

    private final AlbumReviewService albumReviewService;
    private final AlbumService albumService;

    @GetMapping("{id}")
    public ModelAndView loadReviewPage(ModelAndView modelAndView, @PathVariable("id") Long albumId) {
        modelAndView.addObject("album", albumService.findById(albumId));
        modelAndView.addObject("albumReview", albumReviewService.findAllReviewByAlbum(albumId)); // 전체 리뷰 다 가져와야한다.
        modelAndView.setViewName("album-review");

        return modelAndView;
    }

    @GetMapping("write/{id}") // 쓰기 페이지 돌입
    public ModelAndView loadWriteReviewPage(ModelAndView modelAndView, @PathVariable("id") Long albumId) {
        modelAndView.addObject("album", albumService.findById(albumId));
        modelAndView.setViewName("write-album-review");

        return modelAndView;
    }

    @PostMapping("{id}")
    public ModelAndView saveReview(ModelAndView modelAndView, @PathVariable("id") Long albumId,
                                   AlbumReviewRequestDto albumReviewRequestDto, Principal principal) {
        albumReviewService.save(albumId, albumReviewRequestDto, principal.getName());
        modelAndView.setViewName("redirect:/album/review/" + albumId); // 기존 url 로

        return modelAndView;
    }

    @GetMapping("detail/{id}")
    public ModelAndView loadReviewDetailPage(ModelAndView modelAndView, @PathVariable("id") Long reviewId) {
        // reviewId 를 불러오면, 이제 거기서 album 도 불러올 수 있기 때문에 상관없음
        modelAndView.addObject("albumReview", albumReviewService.getDetailReview(reviewId));
        modelAndView.setViewName("album-review-detail");

        return modelAndView;
    }
}
