package com.skhu.practice.Controller;


import com.skhu.practice.Sevice.NotableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class NotableController {
    private final NotableService notableService;
    @GetMapping("notableHome")
    public ModelAndView NotableList(){
        ModelAndView mv = new ModelAndView("notableHome");
        mv.addObject("notableList", notableService.NotableHome());
        return mv;
    }

}
