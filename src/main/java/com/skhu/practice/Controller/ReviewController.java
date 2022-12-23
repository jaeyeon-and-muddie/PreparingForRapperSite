package com.skhu.practice.Controller;

import com.skhu.practice.Repository.AlbumRepository;
import com.skhu.practice.Sevice.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final AlbumService albumService;
    @RequestMapping("home")
    public ModelAndView home(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        mv.addObject("albums",albumService.albumList());

        return mv;
    }
}