package com.skhu.practice.entity;

import com.skhu.practice.dto.SalesResponseDto;
import com.skhu.practice.entity.base.BaseEntity;
import com.skhu.practice.service.DateToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
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
@Table(name = "sales")
public class Sales extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "seller")
    private Users seller;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "purchaser")
    private Users purchaser;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product")
    private Product product;

    @Column(name = "buysCount")
    private Long buysCount;

    @Column(name = "remain_stock")
    private Long remainStock;

    public SalesResponseDto toResponseDto() {
        return SalesResponseDto.builder()
                .id(this.id)
                .purchaser(this.purchaser.toResponseDto())
                .product(this.product.toResponseDto())
                .buysCount(buysCount)
                .buyDate(DateToString.dateToString(this.getCreatedDate()))
                .totalPrice(this.product.totalPrice(buysCount))
                .remainStock(this.product.remainStock(buysCount))
                .build();
    }
}
