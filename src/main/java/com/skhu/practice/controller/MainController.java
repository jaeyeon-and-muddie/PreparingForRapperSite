package com.skhu.practice.controller;

import com.skhu.practice.service.InsertMokDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final InsertMokDataService insertMokDataService;
    @GetMapping("")
    public ModelAndView loadMainPage(ModelAndView modelAndView) {
        modelAndView.setViewName("redirect:/album");
        insertMokDataService.setMokData();
        return modelAndView;
    }
}
