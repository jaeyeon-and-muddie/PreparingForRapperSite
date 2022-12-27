package com.skhu.practice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("album")
public class AlbumController {

    @GetMapping("")
    public ModelAndView loadAlbumBoardPage(ModelAndView modelAndView) {
        modelAndView.setViewName("album-board");
        return modelAndView;
    }
}
