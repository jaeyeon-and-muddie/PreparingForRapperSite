package com.skhu.practice.controller;

import com.skhu.practice.service.AlbumCommentService;
import com.skhu.practice.service.AlbumNestedCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("album")
@RequiredArgsConstructor
public class AlbumCommentController {

    private final AlbumCommentService albumCommentService;
    private final AlbumNestedCommentService albumNestedCommentService;

    @GetMapping("nested/write/{album}/{comment}")
    public ModelAndView loadWriteNestedPage(ModelAndView modelAndView, @PathVariable("album") Long albumId, @PathVariable("comment") Long commentId) {
        modelAndView.setViewName("write-album-nested-comment");
        modelAndView.addObject("albumId", albumId);
        modelAndView.addObject("commentId", commentId);

        return modelAndView;
    }

    @PostMapping("comment/{id}")
    public ModelAndView saveComment(ModelAndView modelAndView, @PathVariable("id") Long albumId, String content, Principal principal) {
        albumCommentService.save(albumId, content, principal.getName());
        modelAndView.setViewName("redirect:/album/detail/" + albumId);
        return modelAndView;
    } // 여기에 tag 검사 추가

    @PostMapping("nested/{album}/{comment}")
    public ModelAndView saveNestedComment(ModelAndView modelAndView, @PathVariable("mixtape") Long albumId,
                                          @PathVariable("comment") Long commentId, String content, Principal principal) {
        albumNestedCommentService.save(commentId, content, principal.getName());
        modelAndView.setViewName("redirect:/album/detail/" + albumId);
        return modelAndView;
    } // 여기에 tag 검사 추가
}
