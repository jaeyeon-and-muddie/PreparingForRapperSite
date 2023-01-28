package com.skhu.practice.controller;

import com.skhu.practice.dto.AlbumReviewRequestDto;
import com.skhu.practice.dto.MixTapeReviewRequestDto;
import com.skhu.practice.service.MixTapeReviewService;
import com.skhu.practice.service.MixTapeService;
import com.skhu.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("mixtape/review")
public class MixTapeReviewController {

    private final UserService userService;
    private final MixTapeService mixTapeService;
    private final MixTapeReviewService mixTapeReviewService;

    @GetMapping("{id}")
    public ModelAndView loadReviewPage(HttpServletRequest request, ModelAndView modelAndView,
                                       @PathVariable("id") Long mixTapeId, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.addObject("mixTape", mixTapeService.findById(mixTapeId));
        modelAndView.addObject("mixTapeReview", mixTapeReviewService.findAllReviewByAlbum(mixTapeId)); // 전체 리뷰 다 가져와야한다.
        modelAndView.setViewName("mixtape-review");

        return modelAndView;
    }

    @GetMapping("write/{id}") // 쓰기 페이지 돌입
    public ModelAndView loadWriteReviewPage(HttpServletRequest request, ModelAndView modelAndView,
                                            @PathVariable("id") Long mixTapeId, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.addObject("mixTape", mixTapeService.findById(mixTapeId));
        modelAndView.setViewName("write-mixtape-review");

        return modelAndView;
    }

    @PostMapping("{id}")
    public ModelAndView saveReview(ModelAndView modelAndView, @PathVariable("id") Long mixTapeId,
                                   MixTapeReviewRequestDto mixTapeReviewRequestDto, Principal principal) {
        mixTapeReviewService.save(mixTapeId, mixTapeReviewRequestDto, principal.getName());
        modelAndView.setViewName("redirect:/mixtape/review/" + mixTapeId); // 기존 url 로
        return modelAndView;
    }

    @GetMapping("detail/{id}")
    public ModelAndView loadReviewDetailPage(HttpServletRequest request, ModelAndView modelAndView,
                                             @PathVariable("id") Long reviewId, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.addObject("mixTapeReview", mixTapeReviewService.getDetailReview(reviewId));
        modelAndView.setViewName("mixtape-review-detail.html");

        return modelAndView;
    }
}
