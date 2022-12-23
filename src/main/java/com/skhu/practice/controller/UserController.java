package com.skhu.practice.controller;

import com.skhu.practice.dto.UserLoginDto;
import com.skhu.practice.entity.User;
import com.skhu.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor // 빈을 알아서 주입해줌
public class UserController {

    private final UserService userService;
    
    @RequestMapping("")
    public ModelAndView loadLoginPage(ModelAndView modelAndView) { // 그냥 바로 login 화면으로
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("login")
    public ModelAndView login(ModelAndView redirect, UserLoginDto userLoginDto, HttpSession session) {
        User user = userService.login(userLoginDto); // 일단 board 로 설정

        if (user != null) {
            redirect.setViewName("redirect:review");
            saveSessionOnSuccess(user, session);
        }

        if (user == null) {
            redirect.setViewName("redirect:login");
        }

        return redirect;
    }

    private void saveSessionOnSuccess(User user, HttpSession session) {
        session.setAttribute("user", user);
    }
}
