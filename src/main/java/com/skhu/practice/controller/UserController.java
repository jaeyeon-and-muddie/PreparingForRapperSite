package com.skhu.practice.controller;

import com.skhu.practice.dto.UserLoginDto;
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
    public ModelAndView loadLoginPage() { // 그냥 바로 login 화면으로
        return new ModelAndView("login");
    }

    @RequestMapping("login")
    public ModelAndView login(UserLoginDto userLoginDto, HttpSession session) {
        ModelAndView redirect = new ModelAndView(userService.login(userLoginDto)); // 일단 board 로 설정
        saveSessionOnSuccess(userLoginDto, redirect, session);

        return redirect;
    }

    private void saveSessionOnSuccess(UserLoginDto userLoginDto, ModelAndView redirect, HttpSession session) {
        if (redirect.getViewName().equals("redirect:review")) {
            session.setAttribute("user", userLoginDto); // 나중에는 뭐 더 완벽한 userLogin 객체로 할 수도 있을 듯
        }
    }
}
