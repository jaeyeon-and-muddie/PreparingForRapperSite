package com.skhu.practice.controller;

import com.skhu.practice.dto.UserSignupDto;
import com.skhu.practice.service.AlarmService;
import com.skhu.practice.service.AlbumService;
import com.skhu.practice.service.UrlToTitleService;
import com.skhu.practice.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor // 빈을 알아서 주입해줌
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final AlarmService alarmService;

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

    @GetMapping("detail/{id}")
    public ModelAndView loadArtistPage(HttpServletRequest request, ModelAndView modelAndView,
                                       @PathVariable("id") Long userId, Principal principal) {
        modelAndView.addObject("visited", userService.userVisited(principal, request.getRequestURL().toString()));
        modelAndView.addObject("artist", userService.findById(userId));
        modelAndView.setViewName("artist-detail"); // User 와 연관한 애들만을 가져와야함
        return modelAndView;
    }

    @GetMapping("alarm")
    public ModelAndView loadAlarmPage(HttpServletRequest request, ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("username", principal.getName());
        modelAndView.addObject("visited", userService.userVisited(principal, request.getRequestURL().toString()));
        modelAndView.addObject("alarms", alarmService.findAllAlarm(principal));
        modelAndView.setViewName("alarm");

        return modelAndView;
    }

    @GetMapping("alarm/{id}")
    public ModelAndView redirectPageByAlarm(ModelAndView modelAndView, @PathVariable("id") Long alarmId) {
        modelAndView.setViewName("redirect:/" + alarmService.redirectByAlarm(alarmId)); // 그래도 visit 할 때에만 저장하는 것이 나을것임
        return modelAndView;
    }
}
