package com.skhu.practice.controller;

import com.skhu.practice.service.MixTapeCommentService;
import com.skhu.practice.service.MixTapeNestedCommentService;
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

    private final MixTapeCommentService mixTapeCommentService;
    private final MixTapeNestedCommentService mixTapeNestedCommentService;

    @GetMapping("nested/write/{mixtape}/{comment}")
    public ModelAndView loadWriteNestedPage(ModelAndView modelAndView, @PathVariable("mixtape") Long mixTapeId, @PathVariable("comment") Long commentId) {
        modelAndView.setViewName("write-album-nested-comment");
        modelAndView.addObject("mixTapeId", mixTapeId);
        modelAndView.addObject("commentId", commentId);

        return modelAndView;
    }

    @PostMapping("comment/{id}")
    public ModelAndView saveComment(ModelAndView modelAndView, @PathVariable("id") Long mixTapeId, String content, Principal principal) {
        mixTapeCommentService.save(mixTapeId, content, principal.getName());
        modelAndView.setViewName("redirect:/mixTape/detail/" + mixTapeId);

        return modelAndView;
    } // 여기에 tag 검사 추가

    @PostMapping("nested/{mixtape}/{comment}")
    public ModelAndView saveNestedComment(ModelAndView modelAndView, @PathVariable("album") Long mixTapeId,
                                          @PathVariable("comment") Long commentId, String content, Principal principal) {
        mixTapeNestedCommentService.save(commentId, content, principal.getName());
        modelAndView.setViewName("redirect:/mixtape/detail/" + mixTapeId);

        return modelAndView;
    } // 여기에 tag 검사 추가
}
