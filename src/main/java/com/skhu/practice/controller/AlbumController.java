package com.skhu.practice.controller;

import com.skhu.practice.dto.AlbumRequestDto;
import com.skhu.practice.service.AlbumCommentService;
import com.skhu.practice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("album")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumCommentService albumCommentService;

    @GetMapping("")
    public ModelAndView loadAlbumBoardPage(ModelAndView modelAndView) {
        modelAndView.setViewName("album-board"); // 여기서 album 들을 쏵 보여줘야 한단 말이야
        modelAndView.addObject("album", albumService.findAll());

        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()") // 이런 식으로 ADMIN 만 접속하도록, 혹은 로그인한 유저만 접속하도록 통제가 가능하다.
    @GetMapping("write")
    public ModelAndView loadAlbumWritePage(ModelAndView modelAndView) {
        modelAndView.setViewName("write-album");
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("write")
    public ModelAndView saveAlbum(ModelAndView modelAndView, AlbumRequestDto albumRequestDto, Principal principal) {
        String viewName = "redirect:/album";

        if (!albumService.save(albumRequestDto, principal.getName())) { // save 시에, 실패하는 경우에만 실행
            viewName = "redirect:/album/write";
        }

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @GetMapping("detail/{id}")
    public ModelAndView loadAlbumDetailPage(ModelAndView modelAndView, @PathVariable("id") Long id) {
        modelAndView.addObject("album", albumService.albumDetail(id));
        modelAndView.addObject("albumComment", albumCommentService.findAllCommentByAlbum(id));
        modelAndView.setViewName("album-detail");
        return modelAndView;
    }
}
