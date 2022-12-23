package com.skhu.practice.Controller;

import com.skhu.practice.DTO.UserLoginDto;
import com.skhu.practice.Entity.User;
import com.skhu.practice.Sevice.AlbumService;
import com.skhu.practice.Sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor // 빈을 알아서 주입해줌
public class UserController {

    private final UserService userService;
    private final AlbumService albumService;

    @RequestMapping("")
    public ModelAndView firstPage() { // 그냥 바로 login 화면으로
        return new ModelAndView("login");
    }

    @RequestMapping("login")
    public ModelAndView login(UserLoginDto userLoginDto) {
        // login -> board
        ModelAndView redirect = new ModelAndView("redirect:"+userService.login(userLoginDto)); // 일단 board 로 설정
//        redirect.addObject("albums",albumService.albumList());

        return redirect;
    }

}