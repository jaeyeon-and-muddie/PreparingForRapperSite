package com.skhu.practice.controller;

import com.skhu.practice.dto.UserLoginDto;
import com.skhu.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor // 빈을 알아서 주입해줌
public class UserController {

    private final UserService userService;
    
    @RequestMapping("")
    public ModelAndView loadLoginPage() { // 그냥 바로 login 화면으로
        return new ModelAndView("login");
    }

    @RequestMapping("login")
    public ModelAndView login(UserLoginDto userLoginDto) {
        ModelAndView redirect = new ModelAndView(userService.login(userLoginDto)); // 일단 board 로 설정

        return redirect;
    }
}
