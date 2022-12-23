package com.skhu.practice.controller;

import com.skhu.practice.dto.albumnotice.AlbumNoticeRequestDto;
import com.skhu.practice.dto.UserLoginDto;

import com.skhu.practice.entity.User;
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

    // 게시물 번호 부분을 수정해야할 것 같음, 게시물이 삭제되었을 경우 게시물 번호가 꼬여버림

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
    public ModelAndView postByReviewId(ModelAndView modelAndView, HttpSession session, @PathVariable(name = "id") Long reviewId) {
        modelAndView.setViewName("post");
        modelAndView.addObject("user", getUser(session));
        modelAndView.addObject("albumNoticePost", reviewService.getPostByPostNumber(reviewId));

        return modelAndView;
    }

    @GetMapping("rewrite/{id}")
    public ModelAndView loadRewriteReviewPage(ModelAndView modelAndView, @PathVariable(name = "id") Long id) {
        modelAndView.setViewName("rewrite-review-page.html");
        modelAndView.addObject("id", id);
        modelAndView.addObject("content", reviewService.getContentByPostNumber(id));

        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteReview(ModelAndView modelAndView, @PathVariable(name = "id") Long id) {
        modelAndView.setViewName("redirect:/review");
        reviewService.delete(id);

        return modelAndView;
    }

    @PostMapping("")
    public ModelAndView saveContent(ModelAndView modelAndView, HttpSession session, AlbumNoticeRequestDto albumNoticeRequestDto) {
        reviewService.saveAlbumAndNotice(getUser(session), albumNoticeRequestDto);
        modelAndView.setViewName("redirect:review"); // 기존 url 로

        return modelAndView;
    }

    @PostMapping("rewrite/{id}")
    public ModelAndView afterRewriteLoadReviewPage(ModelAndView modelAndView, @PathVariable(name = "id") Long id, String content) {
        reviewService.update(id, content);
        modelAndView.setViewName("redirect:/review");

        return modelAndView;
    }

    private User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
