package com.skhu.practice.Controller;


import com.skhu.practice.DTO.NavbarDto;
import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Sevice.Album.NotableService;
import com.skhu.practice.Sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class NotableController {
    private final NotableService notableService;
    private final UserService userService;
    @GetMapping("notableHome")
    public ModelAndView NotableList(@ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("notableHome");
        mv.addObject("notableList", notableService.NotableHome());
        mv.addObject("navbar", userService.navbar(user));
        return mv;
    }

}
