package com.skhu.practice.controller;

import com.skhu.practice.dto.AlbumRequestDto;
import com.skhu.practice.service.AlbumService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
        modelAndView.setViewName("album-board"); // 여기서 album 들을 쏵 보여줘야 한단 말이야
        modelAndView.addObject("album", albumService.findAll());

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

        if (!albumService.save(albumRequestDto)) { // save 시에, 실패하는 경우에만 실행
            viewName = "redirect:/album/write";
        }

        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @GetMapping("detail/{id}")
    public ModelAndView loadAlbumDetailPage(ModelAndView modelAndView, @PathVariable("id") Long id) {
        // id 로 조회해서 detail 가져오면 됨, 여기서 그냥 또 albumRequestDto 재탕하면 된다, 그냥 findById 로 찾아서 돌려주면 끝
        modelAndView.addObject("album", albumService.findById(id));
        modelAndView.setViewName("album-detail");
        return modelAndView;
    }
}
