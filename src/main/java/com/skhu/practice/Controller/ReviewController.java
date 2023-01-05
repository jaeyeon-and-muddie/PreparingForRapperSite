package com.skhu.practice.Controller;

import com.skhu.practice.Entity.Album;
import com.skhu.practice.Entity.User;
import com.skhu.practice.Repository.AlbumRepository;
import com.skhu.practice.Repository.UserRepository;
import com.skhu.practice.Sevice.AlbumService;
import com.skhu.practice.Sevice.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final AlbumService albumService;
    private final UserRepository userRepository;
    private final ReviewService reviewService;
    @GetMapping("home")
    public ModelAndView home(@CookieValue(name = "memberEmail", required = false) String memberEmail){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        User user = userRepository.findByEmail(memberEmail).orElse(null);
        mv.addObject("albums",albumService.albumList());
        mv.addObject("User", user);

        return mv;
    }
    @GetMapping("albumInform")
    public ModelAndView albumInform(@RequestParam("albumId") Long albumId){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("albumInform");
        mv.addObject("album", albumService.albumInform(albumId));
        return mv;
    }
    @GetMapping("reviews")
    public ModelAndView reviews(@RequestParam("albumId") Long albumId){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("reviews");
        mv.addObject("albumReviews", reviewService.albumReview(albumId));
        return mv;
    }
    @GetMapping("reviewDetail")
    public ModelAndView reviewDetail(@RequestParam("albumReviewId") Long albumReviewId){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("reviewDetail");
        mv.addObject("songReviews", reviewService.songReview(albumReviewId));
        return mv;
    }
}