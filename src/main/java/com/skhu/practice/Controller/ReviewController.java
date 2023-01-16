package com.skhu.practice.Controller;

import com.skhu.practice.DTO.ReviewCreateDto;
import com.skhu.practice.Entity.Album;
import com.skhu.practice.Entity.AlbumReview;
import com.skhu.practice.Entity.Comment;
import com.skhu.practice.Entity.User;
import com.skhu.practice.Repository.AlbumRepository;
import com.skhu.practice.Repository.AlbumReviewRepository;
import com.skhu.practice.Repository.UserRepository;
import com.skhu.practice.Sevice.AlbumService;
import com.skhu.practice.Sevice.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final AlbumService albumService;
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final ReviewService reviewService;
    private final AlbumReviewRepository albumReviewRepository;

    @GetMapping("home")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        mv.addObject("albums",albumService.albumList());

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
        mv.addObject("albumId", albumId);
        return mv;
    }
    @GetMapping("reviewDetail")
    public ModelAndView reviewDetail(@RequestParam("albumReviewId") Long albumReviewId){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("reviewDetail");
        AlbumReview albumReview= albumReviewRepository.findById(albumReviewId).orElse(null);
//        List<String> albumReviews = albumReview.getReviews();
        System.out.println(albumReview.getReviews().size());
        mv.addObject("albumReview", albumReview);
        return mv;
    }
    @PostMapping("reviewComment")
    public ModelAndView reviewDetail(@CookieValue(name = "memberEmail", required = false) String memberEmail,
                                     Comment commented, Long albumId){
        AlbumReview albumReview = albumReviewRepository.findById(albumId).orElse(null);
        commented.setAlbumReview(albumReview);
//        System.out.println(memberEmail);
//        System.out.println(commented.getComment());
        commented.setUser(userRepository.findByEmail(memberEmail).orElse(null));
//        System.out.println(commented.getAlbumReview().getId());
        reviewService.reviewComment(commented);
        ModelAndView mv = new ModelAndView("redirect:reviewDetail?albumReviewId="+albumId);
        return mv;

    }
    @GetMapping("reviewCreate")
    public ModelAndView reviewCreate(@RequestParam("albumId") Long albumId,
                                     ReviewCreateDto reviewCreateDto){
        ModelAndView mv= new ModelAndView();
        mv.setViewName("reviewCreate");
        Album album = albumRepository.findById(albumId).orElse(null);
        int number = album.getNumberOfSongs();
        List<String> albumReview = new ArrayList<String>(number);
        for(int i=0; i<number; i++){
            albumReview.add("");
        }
        reviewCreateDto.setReviews(albumReview);
        System.out.println(reviewCreateDto.getReviews().size());
//        reviewCreateDto.setReviews();
        mv.addObject("albumReview",reviewCreateDto);
        mv.addObject("albumId", albumId);

        return mv;
    }
    @PostMapping("reviewCreate")
    public ModelAndView albumReview(@ModelAttribute("reviewCreate") ReviewCreateDto reviewCreateDto,
                                    @CookieValue(name = "memberEmail", required = false) String memberEmail){
        ModelAndView mv = new ModelAndView("redirect:reviews?albumId="+reviewCreateDto.getAlbumId());
        User user = userRepository.findByEmail(memberEmail).orElse(null);
        reviewService.albumReviewCreate(reviewCreateDto, user);
        return mv;
    }

}