package com.skhu.practice.Controller;


import com.skhu.practice.DTO.MarketDto.BasketListDto;
import com.skhu.practice.DTO.MarketDto.PlusPointDto;
import com.skhu.practice.DTO.NavbarDto;
import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Sevice.Market.MarketService;
import com.skhu.practice.Sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
@RequestMapping("market")
public class MarketController {
    private final MarketService marketService;
    private final UserService userService;


    @GetMapping("point")
    public ModelAndView pointHome(@ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("market/pointHome");
        mv.addObject("userPoint", marketService.pointHome(user));
        return mv;
    }
    @GetMapping("plusPoint")
    public ModelAndView plusPoint(PlusPointDto plusPoint){
        ModelAndView mv = new ModelAndView("market/plusPoint");
        mv.addObject("plusPoint", plusPoint);
        return mv;
    }
    @PostMapping("payCheck")
    public ModelAndView payCheck(@ModelAttribute("plusPoint") PlusPointDto plusPoint,@ModelAttribute("user") UserSessionDto user){
        System.out.println(plusPoint.getMoney());
        marketService.payCheck(plusPoint, user);
        ModelAndView mv = new ModelAndView("redirect:point");
        return mv;
    }
    @GetMapping("merchandise")
    public ModelAndView merchandise(@ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("market/merchandise");
        mv.addObject("navbar", userService.navbar(user));
        mv.addObject("merchandises",marketService.merchandise());

        return mv;
    }
    @GetMapping("merchandiseDetail")
    public ModelAndView merchandiseDetail(BasketListDto basketDto,@RequestParam("merchandiseId")Long merchandiseId,@ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("market/merchandiseDetail");
        mv.addObject("basketListDto", basketDto);
        mv.addObject("merchandiseDetail", marketService.merchandiseDetail(merchandiseId));
        mv.addObject("navbar", userService.navbar(user));

        return mv;
    }
    @PostMapping("merchandiseBasket")
    public ModelAndView merchandiseBasket(@ModelAttribute("basketListDto")BasketListDto basketListDto,@ModelAttribute("user") UserSessionDto user){
        marketService.addBasket(basketListDto.getMerchandiseId(),basketListDto.getNum(),user);
        ModelAndView mv= new ModelAndView("redirect:merchandiseDetail?merchandiseId="+basketListDto.getMerchandiseId());
        return mv;
    }
    @GetMapping("buy")
    public ModelAndView merchandiseBuy(@ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("market/buy");
        mv.addObject("buyList",marketService.buyList(user));
        return mv;
    }
    @PostMapping("delete")
    public ModelAndView merchandiseDelete(@ModelAttribute("basketId") Long basketId, @ModelAttribute("user") UserSessionDto user){
        ModelAndView mv = new ModelAndView("redirect:buy");
        marketService.deleteBasket(basketId);

        return mv;
    }
    @PostMapping("buyBasket")
    public ModelAndView buyBasket(@ModelAttribute("user") UserSessionDto user, @ModelAttribute("sum") Long sum)throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        String failMent = marketService.buyBasket(user,sum);
        String failMent1 = URLEncoder.encode(failMent, "UTF-8");
        System.out.println(failMent.length());
        if (failMent.length()==14){
            mv.setViewName("redirect:merchandise");
        }
        else {
            mv.setViewName("redirect:failResult?fail="+failMent1);
        }
        return mv;
    }
    @GetMapping("failResult")
    public ModelAndView result(@RequestParam("fail")String failMent){
        ModelAndView mv =new ModelAndView("market/fail");
        mv.addObject("failMent", failMent);
        return mv;
    }
}
