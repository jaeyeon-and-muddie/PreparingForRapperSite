package com.skhu.practice.entity;

import com.skhu.practice.dto.PurchasesResponseDto;
import com.skhu.practice.entity.base.BaseEntity;
import com.skhu.practice.repository.PurchasesRepository;
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
@Table(name = "purchases")
public class Purchases extends BaseEntity {

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

    public PurchasesResponseDto toResponseDto() {
        return PurchasesResponseDto.builder()
                .id(this.id)
                .seller(this.seller.toResponseDto())
                .product(this.product.toResponseDto())
                .buysCount(this.buysCount)
                .totalPrice(this.product.totalPrice(buysCount))
                .buyDate(DateToString.dateToString(this.getCreatedDate()))
                .build();
    }
}
