package com.skhu.practice.controller;

import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor // 빈을 알아서 주입해줌
public class UserController {

    private final UserRepository userRepository;
    
    @RequestMapping("")
    public ModelAndView firstPage() { // 그냥 바로 login 화면으로
        System.out.println(userRepository.findAll());
        return new ModelAndView("login");
    }

    @RequestMapping("login")
    public ModelAndView login() {
        // login -> board
        ModelAndView redirect = new ModelAndView("board"); // 일단 board 로 설정
        return redirect;
    }
}
