package com.skhu.practice.Controller;

import com.skhu.practice.DTO.MixtapeDto.MixtapeCreateDto;
import com.skhu.practice.DTO.NavbarDto;
import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Sevice.Mixtape.MixtapeService;
import com.skhu.practice.Sevice.UserService;
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
    private final UserService userService;
    @GetMapping("mixtapehome")
    public ModelAndView home(@ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("mixtape/mixtapehome");
        mv.addObject("mixtapeIntroList",mixtapeService.mixtapeIntroList());
        mv.addObject("navbar", userService.navbar(user));
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
        System.out.println(id);
        mixtapeService.recommend(id, user);
        ModelAndView mv = new ModelAndView("redirect:mixtapehome");

        return mv;
    }
    @GetMapping("mixtapes")
    public ModelAndView mixtapes(@ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("mixtape/mixtapes");
        mv.addObject("mixtapeList", mixtapeService.mixtapeList());
        mv.addObject("navbar", userService.navbar(user));
        return mv;
    }

    @GetMapping("mixtapeDetail")
    public ModelAndView mixtapes(Long mixtapeId, @ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("mixtape/mixtapeDetail");
        mv.addObject("mixtapeDetail", mixtapeService.mixtapeDetail(mixtapeId));
        mv.addObject("navbar", userService.navbar(user));
        return mv;
    }

}
