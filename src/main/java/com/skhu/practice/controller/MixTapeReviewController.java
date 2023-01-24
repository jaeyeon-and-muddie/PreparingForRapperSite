package com.skhu.practice.controller;

import com.skhu.practice.dto.AlbumReviewRequestDto;
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

    @GetMapping("{id}")
    public ModelAndView loadReviewPage(HttpServletRequest request, ModelAndView modelAndView,
                                       @PathVariable("id") Long albumId, Principal principal) {
        modelAndView.addObject("visited", userService.userVisited(principal, request.getRequestURL().toString()));

        return modelAndView;
    }

    @GetMapping("write/{id}") // 쓰기 페이지 돌입
    public ModelAndView loadWriteReviewPage(HttpServletRequest request, ModelAndView modelAndView,
                                            @PathVariable("id") Long albumId, Principal principal) {
        modelAndView.addObject("visited", userService.userVisited(principal, request.getRequestURL().toString()));

        return modelAndView;
    }

    @PostMapping("{id}")
    public ModelAndView saveReview(ModelAndView modelAndView, @PathVariable("id") Long albumId,
                                   AlbumReviewRequestDto albumReviewRequestDto, Principal principal) {

        return modelAndView;
    }

    @GetMapping("detail/{id}")
    public ModelAndView loadReviewDetailPage(HttpServletRequest request, ModelAndView modelAndView,
                                             @PathVariable("id") Long reviewId, Principal principal) {
        modelAndView.addObject("visited", userService.userVisited(principal, request.getRequestURL().toString()));

        return modelAndView;
    }
}
