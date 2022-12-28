package com.skhu.practice.controller;

import java.security.Principal;
import java.util.List;

import com.skhu.practice.dto.AlbumReviewRequestDto;

import com.skhu.practice.service.AlbumReviewService;
import com.skhu.practice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/album/review")
@RequiredArgsConstructor
public class AlbumReviewController {

    private final AlbumReviewService albumReviewService;
    private final AlbumService albumService;

    @GetMapping("{id}")
    public ModelAndView loadReviewPage(ModelAndView modelAndView, @PathVariable("id") Long albumId) {
        modelAndView.addObject("album", albumService.findById(albumId));
        modelAndView.addObject("albumReview", albumReviewService.findAllReviewByAlbum(albumId)); // 전체 리뷰 다 가져와야한다.
        modelAndView.setViewName("album-review");

        return modelAndView;
    }

    @GetMapping("write/{id}") // 쓰기 페이지 돌입
    public ModelAndView loadWriteReviewPage(ModelAndView modelAndView, @PathVariable("id") Long albumeId) {
        modelAndView.addObject("album", albumService.findById(albumeId));
        modelAndView.setViewName("write-album-review");

        return modelAndView;
    }

    @PostMapping("{id}")
    public ModelAndView saveReview(ModelAndView modelAndView, @PathVariable("id") Long albumId, AlbumReviewRequestDto albumReviewRequestDto, Principal principal) {
        albumReviewService.saveAlbumReview(albumId, albumReviewRequestDto, principal.getName());
        modelAndView.setViewName("redirect:/album/review/" + albumId); // 기존 url 로

        return modelAndView;
    }

    @GetMapping("rewrite/{id}")
    public ModelAndView loadRewriteReviewPage(ModelAndView modelAndView, @PathVariable(name = "id") Long id) {
        modelAndView.setViewName("rewrite-album.html");
        modelAndView.addObject("id", id);
//        modelAndView.addObject("content", albumReviewService.getContentByPostNumber(id));

        return modelAndView;
    }

    @GetMapping("delete/{id}")
    public ModelAndView deleteReview(ModelAndView modelAndView, @PathVariable(name = "id") Long id) {
        modelAndView.setViewName("redirect:/review");
        albumReviewService.delete(id);

        return modelAndView;
    }

    @PostMapping("rewrite/{id}")
    public ModelAndView afterRewriteLoadReviewPage(ModelAndView modelAndView, @PathVariable(name = "id") Long id, String title, List<String> reviewOfSongs, Double star) {
        albumReviewService.update(id, title, reviewOfSongs, star);
        modelAndView.setViewName("redirect:/review");

        return modelAndView;
    }
}
