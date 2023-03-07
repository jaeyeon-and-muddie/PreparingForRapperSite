package com.skhu.practice.Sevice.Market;


import com.skhu.practice.DTO.MarketDto.*;
import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Entity.User;
import com.skhu.practice.Entity.market.*;
import com.skhu.practice.Repository.Market.BasketRepository;
import com.skhu.practice.Repository.Market.MerchandiseRepository;
import com.skhu.practice.Repository.Market.PaymentRepository;
import com.skhu.practice.Repository.Market.PointRepository;
import com.skhu.practice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MarketService {
    private final PointRepository pointRepository;
    private final MerchandiseRepository merchandiseRepository;
    private final PaymentRepository paymentRepository;

    private final UserRepository userRepository;
    private final BasketRepository basketRepository;

    public PointHomeDto pointHome(UserSessionDto user) {
        User userin =userRepository.findByEmail(user.getEmail()).orElse(null);
        Point point =pointRepository.findByUserId(userin.getId());
        PointHomeDto pointHomeDto = PointHomeDto.builder()
                .havingPoint(point)
                .user(userin)
                .build();
        return pointHomeDto;
    }

    public void payCheck(PlusPointDto plusPoint, UserSessionDto user) {
        User user1= userRepository.findByEmail(user.getEmail()).orElse(null);
        long money = (plusPoint.getMoney())/100;
        Payment p = new Payment();
        if(plusPoint.getHow()==1){
            p=Payment.builder()
                    .what(What.CHARGE)
                    .how(How.KAKAO)
                    .money(plusPoint.getMoney())
                    .user(user1)
                    .build();
            paymentRepository.save(p);
        }
        else if(plusPoint.getHow()==2){
            p=Payment.builder()
                    .what(What.CHARGE)
                    .how(How.GOOGLE)
                    .money(plusPoint.getMoney())
                    .user(user1)
                    .build();
            paymentRepository.save(p);
        }
        else{
            p=Payment.builder()
                    .what(What.CHARGE)
                    .how(How.DEPOSIT)
                    .money(plusPoint.getMoney())
                    .user(user1)
                    .build();
            paymentRepository.save(p);
        }

        Point point1 = pointRepository.findByUserId(user1.getId());
        point1.plusPoint(money);
        pointRepository.save(point1);

    }
    public List<MerchandiseDto> merchandise(){
        List<Merchandise> mcs= merchandiseRepository.findAll();
        List<MerchandiseDto> mcDtos = new ArrayList<>();
        for(int i =0 ; i<mcs.size();i++){
            MerchandiseDto mcDto = MerchandiseDto.builder()
                    .id(mcs.get(i).getId())
                    .image(mcs.get(i).getImage())
                    .point(mcs.get(i).getPoint())
                    .name(mcs.get(i).getName())
                    .build();
            mcDtos.add(mcDto);
        }
        return mcDtos;
    }


    public MerchandiseDetailDto merchandiseDetail(Long merchandiseId) {
        Merchandise merchandise = merchandiseRepository.findById(merchandiseId).orElse(null);
        MerchandiseDetailDto merchandiseDetailDto = MerchandiseDetailDto.builder()
                .lefted(merchandise.getLefted())
                .id(merchandiseId)
                .user(merchandise.getUser())
                .image(merchandise.getImage())
                .name(merchandise.getName())
                .point(merchandise.getPoint())
                .build();
        return merchandiseDetailDto;
    }


    public void addBasket(Long merchandiseId, Long num, UserSessionDto user) {
        User user1 = userRepository.findByEmail(user.getEmail()).orElse(null);
        Merchandise merchandise = merchandiseRepository.findById(merchandiseId).orElse(null);
        Basket basket =Basket.builder()
                .user(user1)
                .num(num)
                .merchandise(merchandise)
                .build();
        basketRepository.save(basket);
    }

    public BuyDto buyList(UserSessionDto user) {
        User user1 = userRepository.findByEmail(user.getEmail()).orElse(null);
        List<Basket> baskets = basketRepository.findAllByUserId(user1.getId());
        Integer sum =0;
        List<BuyListDto> buyList = new ArrayList<>();
        for(int i =0; i<baskets.size(); i++){
            BuyListDto buyListDto = BuyListDto.builder()
                    .basketId(baskets.get(i).getId())
                    .merchandise(baskets.get(i).getMerchandise())
                    .num(baskets.get(i).getNum())
                    .user(user1)
                    .build();
            sum= sum + (baskets.get(i).getMerchandise().getPoint()).intValue();
            buyList.add(buyListDto);
        }
        BuyDto buy = BuyDto.builder()
                .buyListDtoList(buyList)
                .sum(sum)
                .build();
        return buy;
    }

    public void deleteBasket(Long basketId) {
        basketRepository.delete(basketRepository.findById(basketId).orElse(null));
    }

    public String buyBasket(UserSessionDto user, Long sum) {
        User user1= userRepository.findByEmail(user.getEmail()).orElse(null);
        List<Basket> baskets = basketRepository.findAllByUserId(user1.getId());
        List<String> failbasket = new ArrayList<>();
        if((user1.getPoint().getPoint())<sum){
            return "잔액부족";
        }
        String failMent = "상품이 결제 실패되었습니다";
        for(int i =0; i<baskets.size(); i++){
            boolean a =buy(baskets.get(i).getMerchandise().getId(),baskets.get(i).getNum(),user1.getId());
            if(a==false){
                failMent=(baskets.get(i).getMerchandise().getName())+failMent;
               continue;
            }
            sell(baskets.get(i).getMerchandise().getId(),baskets.get(i).getNum());
        }

        return failMent;
    }

    private boolean buy(Long merchandiseId, Long num, long userId) {
        Point point =pointRepository.findByUserId(userId);
        Merchandise merchandise = merchandiseRepository.findById(merchandiseId).orElse(null);
        if(num<=merchandise.getLefted()) {
            merchandise.minusLefted(num);
            merchandiseRepository.save(merchandise);
            point.minusPoint(merchandise.getPoint()*num);
            pointRepository.save(point);
            Payment p=Payment.builder()
                    .what(What.BUY)
                    .how(How.POINT)
                    .money(((merchandise.getPoint())*100)*num)
                    .user(point.getUser())
                    .build();
            paymentRepository.save(p);
            return true;
        }
        else {
            return false;
        }

    }


    public void sell(Long merchandiseId, Long num){
        Merchandise merchandise = merchandiseRepository.findById(merchandiseId).orElse(null);
        Point seller = pointRepository.findByUserId(merchandise.getUser().getId());
        seller.plusPoint(merchandise.getPoint()*num);
        pointRepository.save(seller);
        Payment p=Payment.builder()
                .what(What.SELL)
                .how(How.POINT)
                .money(((merchandise.getPoint())*100)*num)
                .user(seller.getUser())
                .build();
        paymentRepository.save(p);
    }
}
