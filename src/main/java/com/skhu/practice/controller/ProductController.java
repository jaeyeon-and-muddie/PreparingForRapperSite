package com.skhu.practice.controller;

import com.skhu.practice.dto.ProductRequestDto;
import com.skhu.practice.service.ProductService;
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
@RequestMapping("product")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("")
    public ModelAndView loadProductBoardPage(ModelAndView modelAndView, HttpServletRequest request, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.addObject("products", productService.findAll());
        modelAndView.setViewName("product-board");
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("write")
    public ModelAndView loadProductWritePage(ModelAndView modelAndView, HttpServletRequest request, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.setViewName("write-product");
        return modelAndView;
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("write")
    public ModelAndView saveProduct(ModelAndView modelAndView, ProductRequestDto productRequestDto, Principal principal) {
        productService.save(productRequestDto, principal.getName());
        modelAndView.setViewName("redirect:/product");
        return modelAndView;
    }

    @GetMapping("detail/{id}")
    public ModelAndView loadProductDetailPage(ModelAndView modelAndView, HttpServletRequest request,
                                              @PathVariable("id") Long productId, Principal principal) {
        modelAndView.addObject("user", userService.userVisitedAndGetUser(principal, request.getRequestURL().toString()));
        modelAndView.addObject("product", productService.findById(productId));
        modelAndView.setViewName("product-detail");
        return modelAndView;
    }
}
