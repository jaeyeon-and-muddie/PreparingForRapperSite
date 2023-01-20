package com.skhu.practice.controller;

import com.skhu.practice.dto.AlbumRequestDto;
import com.skhu.practice.service.AlbumCommentService;
import com.skhu.practice.service.AlbumService;
import com.skhu.practice.service.UrlToTitleService;
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
@RequestMapping("album")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumCommentService albumCommentService;
    private final UrlToTitleService urlToTitleService;

    @GetMapping("")
    public ModelAndView loadAlbumBoardPage(HttpServletRequest request, ModelAndView modelAndView) {
        System.out.println(urlToTitleService.getTitleByUrl(request.getRequestURL().toString()));
        modelAndView.setViewName("album-board"); // 여기서 album 들을 쏵 보여줘야 한단 말이야
        modelAndView.addObject("album", albumService.findAll());

        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()") // 이런 식으로 ADMIN 만 접속하도록, 혹은 로그인한 유저만 접속하도록 통제가 가능하다.
    @GetMapping("write")
    public ModelAndView loadAlbumWritePage(HttpServletRequest request, ModelAndView modelAndView) {
        System.out.println(urlToTitleService.getTitleByUrl(request.getRequestURL().toString()));
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
    public ModelAndView loadAlbumDetailPage(HttpServletRequest request, ModelAndView modelAndView, @PathVariable("id") Long id) {
        System.out.println(urlToTitleService.getTitleByUrl(request.getRequestURL().toString()));
        modelAndView.addObject("album", albumService.albumDetail(id));
        modelAndView.addObject("albumComment", albumCommentService.findAllCommentByAlbum(id));
        modelAndView.setViewName("album-detail");
        return modelAndView;
    }

    @GetMapping("rate")
    public ModelAndView loadAlbumRatePage(HttpServletRequest request, ModelAndView modelAndView) {
        System.out.println(urlToTitleService.getTitleByUrl(request.getRequestURL().toString()));
        modelAndView.addObject("monthlyAlbum", albumService.findByMonthlyAlbum());
        modelAndView.addObject("topAlbumByAverageOfStar", albumService.findTop5ByAverageOfStar());
        modelAndView.addObject("topAlbumByNumberOfReview", albumService.findTop5ByNumberOfReview());
        modelAndView.addObject("topAlbumByHits", albumService.findTop5ByHits());
        modelAndView.setViewName("album-rate.html");

        return modelAndView;
    }
}
