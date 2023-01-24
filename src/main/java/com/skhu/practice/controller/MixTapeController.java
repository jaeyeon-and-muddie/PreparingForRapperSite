package com.skhu.practice.controller;

import com.skhu.practice.dto.AlbumRequestDto;
import com.skhu.practice.dto.SongInputDto;
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

    @GetMapping("")
    public ModelAndView loadMixTapeBoardPage(HttpServletRequest request, ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("visited", userService.userVisited(principal, request.getRequestURL().toString()));

        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("write")
    public ModelAndView loadMixTapeWritePage(HttpServletRequest request, ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("visited", userService.userVisited(principal, request.getRequestURL().toString()));

        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("write")
    public ModelAndView saveMixTape(ModelAndView modelAndView, AlbumRequestDto albumRequestDto,
                                  SongInputDto song, Principal principal) {
        return modelAndView;
    }

    @GetMapping("detail/{id}")
    public ModelAndView loadMixTapeDetailPage(HttpServletRequest request, ModelAndView modelAndView,
                                            @PathVariable("id") Long id, Principal principal) {
        modelAndView.addObject("visited", userService.userVisited(principal, request.getRequestURL().toString()));

        return modelAndView;
    }
}
