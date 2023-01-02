package com.skhu.practice.Controller;

import com.skhu.practice.Entity.User;
import com.skhu.practice.Repository.AlbumRepository;
import com.skhu.practice.Repository.UserRepository;
import com.skhu.practice.Sevice.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ReviewController {

    private final AlbumService albumService;
    private final UserRepository userRepository;
    @GetMapping("home")
    public ModelAndView home(@CookieValue(name = "memberEmail", required = false) String memberEmail){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("home");
        User user = userRepository.findByEmail(memberEmail).orElse(null);
        mv.addObject("albums",albumService.albumList());
        mv.addObject("User", user);

        return mv;
    }
}