package com.skhu.practice.controller;

import java.util.List;
import com.skhu.practice.dto.albumnotice.AlbumNoticeRequestDto;

import com.skhu.practice.service.AlbumReviewService;
import com.skhu.practice.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/album/review")
@RequiredArgsConstructor
public class AlbumReviewController {

    private final AlbumReviewService albumReviewService;
    private final AlbumService albumService;

    // 게시물 번호 부분을 수정해야할 것 같음, 게시물이 삭제되었을 경우 게시물 번호가 꼬여버림

    @GetMapping("{id}")
    public ModelAndView loadReviewPage(ModelAndView modelAndView, @PathVariable("id") Long albumId) {
        modelAndView.addObject("album", albumService.findById(albumId));
        modelAndView.addObject("albumReview", albumReviewService.findAllReviewByAlbum(albumId)); // 전체 리뷰 다 가져와야한다.
        modelAndView.setViewName("album-review");

        return modelAndView;
    }

    @GetMapping("write")
    public ModelAndView loadWriteReviewPage(ModelAndView modelAndView) {
        modelAndView.setViewName("write-review-page"); // html page naming convention 에 의거한 html page name
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

    @PostMapping("")
    public ModelAndView saveContent(ModelAndView modelAndView, HttpSession session, AlbumNoticeRequestDto albumNoticeRequestDto) {
//        albumReviewService.saveAlbumAndNotice(getUser(session), albumNoticeRequestDto);
        modelAndView.setViewName("redirect:review"); // 기존 url 로

        return modelAndView;
    }

    @PostMapping("rewrite/{id}")
    public ModelAndView afterRewriteLoadReviewPage(ModelAndView modelAndView, @PathVariable(name = "id") Long id, String title, List<String> reviewOfSongs, Double star) {
        albumReviewService.update(id, title, reviewOfSongs, star);
        modelAndView.setViewName("redirect:/review");

        return modelAndView;
    }
}
