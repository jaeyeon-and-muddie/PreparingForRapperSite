package com.skhu.practice.entity;

import com.skhu.practice.dto.BasketResponseDto;
import com.skhu.practice.entity.base.BaseEntity;
import com.skhu.practice.service.DateToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "basket")
public class Basket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "purchaser")
    private Users purchaser;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "seller")
    private Users seller;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product")
    private Product product;

    @Column(name = "buysCount")
    private Long buysCount;

    public boolean isPossibleToPurchase() {
        return this.purchaser.isPossibleToBuy(this.product.totalPrice(this.buysCount))
                && this.product.isPossibleToSale(this.buysCount);
    }

    public String getMessage() {
        return pointShortage(this.purchaser.isPossibleToBuy(this.product.totalPrice(this.buysCount)))
                + stockShortage(this.product.isPossibleToSale(this.buysCount));
    }

    public String stockShortage(boolean isShortage) {
        if (!isShortage) {
            return "재고가 부족합니다.\n";
        }

        return "";
    }

    public String pointShortage(boolean isShortage) {
        if (!isShortage) {
            return "포인트가 부족합니다. \n";
        }

        return "";
    }

    public Purchases toPurchases() {
        return Purchases.builder()
                .seller(this.seller)
                .purchaser(this.purchaser)
                .buysCount(this.buysCount)
                .product(this.product)
                .build();
    }

    public Sales toSales() {
        return Sales.builder()
                .purchaser(this.purchaser)
                .seller(this.seller)
                .product(this.product)
                .buysCount(this.buysCount)
                .remainStock(calculateRemainStock())
                .build();
    }

    private Long calculateRemainStock() {
        return this.product.remainStock(this.buysCount);
    }

    public BasketResponseDto toResponseDto() {
        return BasketResponseDto.builder()
                .id(this.id)
                .purchaser(this.purchaser.toResponseDto())
                .seller(this.seller.toResponseDto())
                .product(this.product.toResponseDto())
                .buysCount(this.buysCount)
                .createdDate(DateToString.dateToString(this.getCreatedDate()))
                .build();
    }
}
