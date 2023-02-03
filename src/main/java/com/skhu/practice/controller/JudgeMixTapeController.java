package com.skhu.practice.controller;

import com.skhu.practice.dto.MixTapeRequestDto;
import com.skhu.practice.dto.SongInputDto;
import com.skhu.practice.service.EmoteService;
import com.skhu.practice.service.JudgeMixTapeService;
import com.skhu.practice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("judge")
public class JudgeMixTapeController {

    private final JudgeMixTapeService judgeMixTapeService;
    private final UserService userService;
    private final EmoteService emoteService;

    @GetMapping("")
    public ModelAndView loadJudgePage(ModelAndView modelAndView, HttpServletRequest request, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.addObject("judgeMixTape", judgeMixTapeService.findAll());
        modelAndView.setViewName("judge");
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("write")
    public ModelAndView saveMixTape(ModelAndView modelAndView, MixTapeRequestDto mixTapeRequestDto,
                                    SongInputDto song, Principal principal) {
        judgeMixTapeService.save(mixTapeRequestDto, song.getSongs(), principal.getName());
        modelAndView.setViewName("redirect:/judge");

        return modelAndView;
    }

    @GetMapping("emote/{isGood}/{id}")
    public ModelAndView clickEmote(ModelAndView modelAndView, Principal principal,
                                   @PathVariable("isGood") Boolean isGood, @PathVariable("id") Long judgeMixTapeId) {
        emoteService.occurEmote(isGood, principal.getName(), judgeMixTapeId);
        modelAndView.setViewName("redirect:/judge");
        return modelAndView;
    }
}
