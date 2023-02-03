package com.skhu.practice.controller;

import com.skhu.practice.dto.PaymentRequestDto;
import com.skhu.practice.dto.UserSignupDto;
import com.skhu.practice.service.AlarmService;
import com.skhu.practice.service.AlbumService;
import com.skhu.practice.service.BasketService;
import com.skhu.practice.service.PaymentService;
import com.skhu.practice.service.PurchasesService;
import com.skhu.practice.service.SalesService;
import com.skhu.practice.service.UrlToTitleService;
import com.skhu.practice.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
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

import javax.management.loading.PrivateClassLoader;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor // 빈을 알아서 주입해줌
@RequestMapping("user")
public class UserController {

    private final UserService userService;
    private final AlarmService alarmService;
    private final PaymentService paymentService;
    private final PurchasesService purchasesService;
    private final SalesService salesService;
    private final BasketService basketService;

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
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.addObject("artist", userService.findById(userId));
        modelAndView.setViewName("artist-detail"); // User 와 연관한 애들만을 가져와야함
        return modelAndView;
    }

    @GetMapping("alarm")
    public ModelAndView loadAlarmPage(HttpServletRequest request, ModelAndView modelAndView, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.addObject("alarms", alarmService.findAllAlarm(principal));
        modelAndView.setViewName("alarm");

        return modelAndView;
    }

    @GetMapping("alarm/{id}")
    public ModelAndView redirectPageByAlarm(ModelAndView modelAndView, @PathVariable("id") Long alarmId) {
        modelAndView.setViewName("redirect:/" + alarmService.redirectByAlarm(alarmId)); // 그래도 visit 할 때에만 저장하는 것이 나을것임
        return modelAndView;
    }

    @GetMapping("point")
    public ModelAndView loadPaymentPage(ModelAndView modelAndView, HttpServletRequest request, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.setViewName("user-point-payment");
        return modelAndView;
    }

    @PostMapping("point")
    public ModelAndView savePayment(ModelAndView modelAndView, PaymentRequestDto paymentRequestDto, Principal principal) {
        paymentService.save(paymentRequestDto, principal.getName());
        modelAndView.setViewName("redirect:/album");
        return modelAndView;
    }

    @GetMapping("point/receipt")
    public ModelAndView loadReceiptPage(ModelAndView modelAndView, HttpServletRequest request, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.addObject("paymentLog", paymentService.findUserLog(principal.getName()));
        modelAndView.addObject("purchases", purchasesService.findAllByUser(principal.getName()));
        modelAndView.setViewName("user-point-payment-log");
        return modelAndView;
    }

    @GetMapping("point/sale")
    public ModelAndView loadSalePage(ModelAndView modelAndView, HttpServletRequest request, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.addObject("sales", salesService.findAllByUser(principal.getName()));
        modelAndView.setViewName("user-point-sale");
        return modelAndView;
    }

    @GetMapping("point/basket")
    public ModelAndView loadBasketPage(ModelAndView modelAndView, HttpServletRequest request, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.addObject("baskets", basketService.findAllByUser(principal.getName()));
        modelAndView.setViewName("user-point-basket");
        return modelAndView;
    }

    @PostMapping("point/basket/{id}")
    public ModelAndView saveBasket(ModelAndView modelAndView, Long buysCount,
                                   @PathVariable("id") Long productId, Principal principal) {
        modelAndView.setViewName("redirect:/user/point/basket");

        if (!basketService.save(buysCount, productId, principal.getName())) {
            modelAndView.setViewName("redirect:/product/detail/" + productId);
        }

        return modelAndView;
    }

    @PostMapping("point/basket/confirm")
    public ModelAndView buyProduct(ModelAndView modelAndView, HttpServletRequest request, Long id, Principal principal) {
        modelAndView.addObject("warningMessage", basketService.confirm(id));
        modelAndView.addObject("baskets", basketService.findAllByUser(principal.getName()));
        modelAndView.addObject("user",
                userService.userVisitedAndGetUser(principal, request.getRequestURL().toString().substring(0, request.getRequestURL().toString().lastIndexOf("/"))));
        modelAndView.setViewName("user-point-basket");
        return modelAndView;
    }

    @PostMapping("point/basket/cancel")
    public ModelAndView dontBuyProduct(ModelAndView modelAndView, Long id) {
        basketService.delete(id);
        modelAndView.setViewName("redirect:/user/point/basket");
        return modelAndView;
    }
}
