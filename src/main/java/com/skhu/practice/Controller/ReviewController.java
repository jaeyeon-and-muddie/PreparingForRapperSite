package com.skhu.practice.Controller;

import com.skhu.practice.DTO.AlbumDto.CommentDto;
import com.skhu.practice.DTO.AlbumDto.ReviewCreateDto;
import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Entity.album.Album;
import com.skhu.practice.Entity.album.Comment;
import com.skhu.practice.Entity.User;
import com.skhu.practice.Repository.Album.AlbumRepository;
import com.skhu.practice.Repository.Album.AlbumReviewRepository;
import com.skhu.practice.Repository.UserRepository;
import com.skhu.practice.Sevice.Album.AlbumService;
import com.skhu.practice.Sevice.Album.ReviewService;
import com.skhu.practice.Sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class ReviewController {

    private final AlbumService albumService;
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final ReviewService reviewService;
    private final AlbumReviewRepository albumReviewRepository;
    private final UserService userService;

    @GetMapping("home")
    public ModelAndView home(@ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        mv.addObject("albums",albumService.albumList());
        mv.addObject("navbar",userService.navbar(user));
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
    public ModelAndView reviews(@RequestParam("albumId") Long albumId,@ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("reviews");
        mv.addObject("albums",albumService.albumList());
        mv.addObject("albumReviews", reviewService.albumReview(albumId));
        mv.addObject("navbar",userService.navbar(user));
        mv.addObject("albumId", albumId);
        return mv;
    }
    @GetMapping("reviewDetail")
    public ModelAndView reviewDetail(@RequestParam("albumReviewId") Long albumReviewId , @ModelAttribute("user") UserSessionDto user
                                     ,CommentDto commented){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("reviewDetail");
        reviewService.updateView(albumReviewId);
        mv.addObject("navbar",userService.navbar(user));
        mv.addObject("albums",albumService.albumList());
        mv.addObject("commented", commented);
        mv.addObject("albumReview", reviewService.findReview(albumReviewId));
        return mv;
    }
    @PostMapping("reviewComment")
    public ModelAndView reviewDetail(@ModelAttribute("user") UserSessionDto user,
                                     CommentDto commented){
        reviewService.reviewComment(commented,user);
        ModelAndView mv = new ModelAndView("redirect:reviewDetail?albumReviewId="+commented.getAlbumReviewId());
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
                                    @ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("redirect:reviews?albumId="+reviewCreateDto.getAlbumId());
        User user1 = userRepository.findByEmail(user.getEmail()).orElse(null);
        reviewService.albumReviewCreate(reviewCreateDto, user1);
        return mv;
    }

}