package com.skhu.practice.service;

import com.skhu.practice.dto.BasketResponseDto;
import com.skhu.practice.entity.Basket;
import com.skhu.practice.entity.Product;
import com.skhu.practice.entity.Users;
import com.skhu.practice.repository.BasketRepository;
import com.skhu.practice.repository.ProductRepository;
import com.skhu.practice.repository.PurchasesRepository;
import com.skhu.practice.repository.SalesRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;
    private final SalesRepository salesRepository;
    private final PurchasesRepository purchasesRepository;
    private final ProductRepository productRepository;

    public List<BasketResponseDto> findAllByUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new)
                .getMyBasket()
                .stream()
                .map(Basket::toResponseDto)
                .collect(Collectors.toList());
    }

    public void delete(Long basketId) {
        basketRepository.delete(basketRepository
                .findById(basketId).orElseThrow(NoSuchElementException::new));
    }

    public String confirm(Long basketId) {
        Basket basket = basketRepository.findById(basketId).orElseThrow(NoSuchElementException::new);
        String message = basket.getMessage();

        if (basket.isPossibleToPurchase()) {
            purchasesRepository.save(basket.toPurchases());
            salesRepository.save(basket.toSales());
            basketRepository.delete(basket);
            deal(basket.getSeller(), basket.getPurchaser(), basket.getProduct().totalPrice(basket.getBuysCount()));
            basket.getProduct().sale(basket.getBuysCount());
            productRepository.save(basket.getProduct());
        }

        return message;
    }

    private void deal(Users seller, Users purchaser, Long totalPrice) {
        seller.saleProduct(totalPrice);
        purchaser.usePoint(totalPrice);
        userRepository.save(seller);
        userRepository.save(purchaser);
    }

    public boolean save(Long buysCount, Long productId, String username) { // 장바구니에 담을때에는 포인트는 신경쓰지말자.
        Product product = productRepository.findById(productId).orElseThrow(NoSuchElementException::new);
        Users user = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);

        if (!product.isPossibleToSale(buysCount)) {
            return false;
        }

        basketRepository.save(Basket.builder()
                .purchaser(user)
                .seller(product.getRegistrant())
                .buysCount(buysCount)
                .product(product)
                .build());

        return true;
    }
}
