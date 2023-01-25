package com.skhu.practice.controller;

import com.skhu.practice.dto.MixTapeRequestDto;
import com.skhu.practice.dto.SongInputDto;
import com.skhu.practice.service.MixTapeCommentService;
import com.skhu.practice.service.MixTapeService;
import com.skhu.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping("mixtape")
public class MixTapeController {

    private final UserService userService;
    private final MixTapeService mixTapeService;
    private final MixTapeCommentService mixTapeCommentService;

    @GetMapping("")
    public ModelAndView loadMixTapeBoardPage(HttpServletRequest request, ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("visited", userService.userVisited(principal, request.getRequestURL().toString()));
        modelAndView.addObject("mixTape", mixTapeService.findAll());
        modelAndView.setViewName("mixtape-board");

        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("write")
    public ModelAndView loadMixTapeWritePage(HttpServletRequest request, ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("visited", userService.userVisited(principal, request.getRequestURL().toString()));
        modelAndView.setViewName("write-mixtape");

        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("write")
    public ModelAndView saveMixTape(ModelAndView modelAndView,  MixTapeRequestDto mixTapeRequestDto,
                                  SongInputDto song, Principal principal) {
        mixTapeService.save(mixTapeRequestDto, song.getSongs(), principal.getName());
        modelAndView.setViewName("redirect:/mixtape");

        return modelAndView;
    }

    @GetMapping("detail/{id}")
    public ModelAndView loadMixTapeDetailPage(HttpServletRequest request, ModelAndView modelAndView,
                                            @PathVariable("id") Long id, Principal principal) {
        modelAndView.addObject("visited", userService.userVisited(principal, request.getRequestURL().toString()));
        modelAndView.addObject("mixTape", mixTapeService.mixTapeDetail(id));
        modelAndView.addObject("mixTapeComment", mixTapeCommentService.findAllCommentByAlbum(id));
        modelAndView.setViewName("mixtape-detail");

        return modelAndView;
    }
}
