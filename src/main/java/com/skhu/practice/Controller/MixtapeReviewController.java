package com.skhu.practice.Controller;

import com.skhu.practice.DTO.MixtapeDto.MixtapeReviewCreateDto;
import com.skhu.practice.DTO.NavbarDto;
import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Entity.mixtape.MixtapeComment;
import com.skhu.practice.Entity.mixtape.MixtapeReview;
import com.skhu.practice.Repository.Mixtape.MixtapeRepository;
import com.skhu.practice.Repository.Mixtape.MixtapeReviewRepository;
import com.skhu.practice.Repository.UserRepository;
import com.skhu.practice.Sevice.Mixtape.MixtapeReviewService;
import com.skhu.practice.Sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
@RequestMapping("mixtape")
public class MixtapeReviewController {
    private final MixtapeReviewService mixtapeReviewService;
    private final MixtapeReviewRepository mixtapeReviewRepository;
    private final UserRepository userRepository;
    private final MixtapeRepository mixtapeRepository;
    private final UserService userService;

    @GetMapping("mixtapeReview")
    public ModelAndView mixtapeReview(Long mixtapeId,@ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("mixtape/mixtapeReviews");
        mixtapeReviewService.updateView(mixtapeId);
        mv.addObject("mixtapeReviews", mixtapeReviewService.reviewHome(mixtapeId));
        mv.addObject("navbar", userService.navbar(user));
        return mv;
    }
    @GetMapping("mixtapeReviewCreate")
    public ModelAndView mixtapeReviewCreate(@RequestParam("mixtapeId") Long mixtapeId,MixtapeReviewCreateDto mixtapeReviewCreateDto){
        ModelAndView mv = new ModelAndView("mixtape/mixtapeReviewCreate");
        mv.addObject("create",mixtapeReviewCreateDto);
        mv.addObject("mixtapeId", mixtapeId);
        return mv;
    }
    @PostMapping("plusMixtapeReview")
    public ModelAndView plusMixtapeReview(@ModelAttribute("create") MixtapeReviewCreateDto create,
                                          @ModelAttribute("user") UserSessionDto user){
        mixtapeReviewService.reviewCreate(create, user);
        ModelAndView mv = new ModelAndView("redirect:mixtapeReview?mixtapeId="+create.getMixtapeId());
        return mv;
    }

    @GetMapping("mixtapeReviewDetail")
    public ModelAndView mixtapeReviewDetail(Long mixtapeReviewId,@ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("mixtape/mixtapeReviewDetail");
        mv.addObject("mixtapeReview", mixtapeReviewService.reviewDetail(mixtapeReviewId));
        mv.addObject("navbar", userService.navbar(user));
        return mv;
    }
    @PostMapping("mixtapeReviewComment")
    public ModelAndView reviewDetail(@ModelAttribute("user") UserSessionDto user,
                                     MixtapeComment commented, Long mixtapeReviewId,
                                     Principal principal){
        MixtapeReview mixtapeReview = mixtapeReviewRepository.findById(mixtapeReviewId).orElse(null);
        commented.setMixtapeReview(mixtapeReview);
        commented.setUser(userRepository.findByEmail(user.getEmail()).orElse(null));
//        System.out.println(commented.getAlbumReview().getId());
        mixtapeReviewService.mixtapeReviewComment(commented);
        ModelAndView mv = new ModelAndView("redirect:mixtapeReviewDetail?mixtapeReviewId="+mixtapeReviewId);
        return mv;

    }
}
