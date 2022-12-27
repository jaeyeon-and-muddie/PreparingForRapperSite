package com.skhu.practice.controller;

import com.skhu.practice.dto.UserSignupDto;
import com.skhu.practice.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor // 빈을 알아서 주입해줌
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping("signup")
    public ModelAndView loadSignupPage(ModelAndView modelAndView) {
        modelAndView.setViewName("signup");
        modelAndView.addObject("userSignup", new UserSignupDto());
        return modelAndView;
    }

    @PostMapping("signup")
    public String signup(UserSignupDto userSignupDto) {
        final String REDIRECT_SIGNUP = "redirect:signup";

        if (userService.isNotEqualsPassword1AndPassword2(userSignupDto)) {
            return REDIRECT_SIGNUP;
        }

        return "redirect:/";
    }
}
