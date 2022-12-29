package com.skhu.practice.controller;

import com.skhu.practice.service.AlbumCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
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

    @PostMapping("comment/{id}")
    public ModelAndView saveComment(ModelAndView modelAndView, @PathVariable("id") Long albumId, String content, Principal principal) {
        albumCommentService.save(albumId, content, principal.getName());
        modelAndView.setViewName("redirect:/album/detail/" + albumId);
        return modelAndView;
    }
}
