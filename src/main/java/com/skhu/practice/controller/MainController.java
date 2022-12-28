package com.skhu.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @GetMapping("")
    public ModelAndView loadMainPage(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/album");
        return modelAndView;
    }
}
