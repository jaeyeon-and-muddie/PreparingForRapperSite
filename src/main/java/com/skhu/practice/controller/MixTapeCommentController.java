package com.skhu.practice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("mixtape/comment")
public class MixTapeCommentController {

    @GetMapping("nested/write/{album}/{comment}")
    public ModelAndView loadWriteNestedPage(ModelAndView modelAndView, @PathVariable("album") Long albumId, @PathVariable("comment") Long commentId) {
        return modelAndView;
    }

    @PostMapping("comment/{id}")
    public ModelAndView saveComment(ModelAndView modelAndView, @PathVariable("id") Long albumId, String content, Principal principal) {
        return modelAndView;
    } // 여기에 tag 검사 추가

    @PostMapping("nested/{album}/{comment}")
    public ModelAndView saveNestedComment(ModelAndView modelAndView, @PathVariable("album") Long albumId,
                                          @PathVariable("comment") Long commentId, String content, Principal principal) {
        return modelAndView;
    } // 여기에 tag 검사 추가
}
