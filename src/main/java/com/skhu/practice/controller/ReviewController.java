package com.skhu.practice.controller;

import com.skhu.practice.dto.albumnotice.AlbumNoticeRequestDto;
import com.skhu.practice.dto.UserLoginDto;

import com.skhu.practice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("")
    public ModelAndView loadReviewPage(ModelAndView modelAndView) {
        modelAndView.addObject("albumNoticeReview", reviewService.getAllReview());
        modelAndView.setViewName("review");

        return modelAndView;
    }

    @GetMapping("write")
    public ModelAndView loadWriteReviewPage(ModelAndView modelAndView) {
        modelAndView.setViewName("write-review-page"); // html page naming convention 에 의거한 html page name
        return modelAndView;
    }

    @GetMapping("{id}") // review.html id
    public ModelAndView postByReviewId(ModelAndView modelAndView, @PathVariable(name = "id") Long reviewId) {
        modelAndView.setViewName("post");
        modelAndView.addObject("albumNoticePost", reviewService.getPostByPostNumber(reviewId));

        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView saveContent(ModelAndView modelAndView, HttpSession session, AlbumNoticeRequestDto albumNoticeRequestDto) {
        reviewService.saveAlbumAndNotice(getUserInformation(session), albumNoticeRequestDto);
        modelAndView.setViewName("redirect:review"); // 기존 url 로

        return modelAndView;
    }

    private UserLoginDto getUserInformation(HttpSession session) {
        return (UserLoginDto) session.getAttribute("user");
    }
}
