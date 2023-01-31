package com.skhu.practice.Controller;

import com.skhu.practice.DTO.MixtapeCreateDto;
import com.skhu.practice.DTO.MixtapeDetailDto;
import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Entity.User;
import com.skhu.practice.Sevice.MixtapeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
@RequestMapping("mixtape")
public class MixtapeController {

    private final MixtapeService mixtapeService;
    @GetMapping("mixtapehome")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView("mixtape/mixtapehome");
        mv.addObject("mixtapeIntroList",mixtapeService.mixtapeIntroList());
        return mv;
    }
    @GetMapping("soundCloud")
    public ModelAndView soundClound(@RequestParam Long mixtapeId){
        ModelAndView mv = new ModelAndView("mixtape/soundCloud");
        mv.addObject("soundCloud", mixtapeService.soundCloud(mixtapeId));
        return mv;
    }
    @GetMapping("mixtapeCreate")
    public ModelAndView create(MixtapeCreateDto mixtapeCreateDto){
        ModelAndView mv = new ModelAndView("mixtape/mixtapeCreate");
        mv.addObject("mixtapeCreate", mixtapeCreateDto);
        return mv;

    }
    @PostMapping("plusMixtape")
    public ModelAndView plusMixtape(@ModelAttribute("plusMixtape") MixtapeCreateDto mixtapeCreate, @ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("redirect:mixtapehome");
        mixtapeService.plusMixtape(mixtapeCreate,user);
        return mv;
    }

    @PostMapping("recommend")
    public ModelAndView recommend(@RequestParam Long id,@ModelAttribute("user") UserSessionDto user){
        System.out.println(user.getEmail());
        mixtapeService.recommend(id, user);
        ModelAndView mv = new ModelAndView("redirect:mixtapehome");

        return mv;
    }
    @GetMapping("mixtapes")
    public ModelAndView mixtapes(){
        ModelAndView mv = new ModelAndView("mixtape/mixtapes");
        mv.addObject("mixtapeList", mixtapeService.mixtapeList());
        return mv;
    }

    @GetMapping("mixtapeDetail")
    public ModelAndView mixtapes(Long mixtapeId){
        ModelAndView mv = new ModelAndView("mixtape/mixtapeDetail");
        mv.addObject("mixtapeDetail", mixtapeService.mixtapeDetail(mixtapeId));
        return mv;
    }

}
