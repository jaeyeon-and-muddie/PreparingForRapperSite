package com.skhu.practice.controller;

import com.skhu.practice.dto.AlbumReviewRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("review")
public class ReviewController {

    @GetMapping("")
    public ModelAndView loadWriteReviewPage() {
        return new ModelAndView("write-review.html-page"); // html page naming convention 에 의거한 html page name
    }

    @GetMapping("{id}") // review.html id
    public ModelAndView postByReviewId(@PathVariable(name = "id") Long reviewId) {
        return new ModelAndView("post");
    }

    @PostMapping("") //
    public ModelAndView saveContent(AlbumReviewRequestDto albumReviewRequestDto) {
        // 사용자가 잘못 입력하였을 때에는 넘어가지 않을 필요도 있을까?
        return null;
    }
}
