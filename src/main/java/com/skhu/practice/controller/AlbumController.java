package com.skhu.practice.controller;

import com.skhu.practice.dto.AlbumRequestDto;
import com.skhu.practice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("album")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("")
    public ModelAndView loadAlbumBoardPage(ModelAndView modelAndView) {
        modelAndView.setViewName("album-board");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')") // 이런 식으로 ADMIN 만 접속하도록, 혹은 로그인한 유저만 접속하도록 통제가 가능하다.
    @GetMapping("write")
    public ModelAndView loadAlbumWritePage(ModelAndView modelAndView) {
        modelAndView.setViewName("write-album");
        return modelAndView;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("write")
    public ModelAndView saveAlbum(ModelAndView modelAndView, AlbumRequestDto albumRequestDto) {
        String viewName = "redirect:/album";
        System.out.println("fuck are you doing");
        System.out.println(albumRequestDto);

        if (!albumService.save(albumRequestDto)) { // save 시에, 실패하는 경우에만 실행
            System.out.println("fuck you");
            viewName = "redirect:/album/write";
        }

        modelAndView.setViewName(viewName);
        return modelAndView;
    }
}
