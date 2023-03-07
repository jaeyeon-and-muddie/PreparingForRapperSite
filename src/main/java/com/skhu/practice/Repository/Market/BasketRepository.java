package com.skhu.practice.Repository.Market;

import com.skhu.practice.Entity.market.Basket;
import com.skhu.practice.Entity.market.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findAllByuserId(long id) ;

    Basket findByUserId(long id);

    List<Basket> findAllByUserId(long id);
}