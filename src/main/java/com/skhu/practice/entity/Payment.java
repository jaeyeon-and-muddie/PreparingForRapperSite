package com.skhu.practice.entity;

import com.skhu.practice.dto.PaymentLogDto;
import com.skhu.practice.entity.base.BaseEntity;
import com.skhu.practice.service.DateToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "payment")
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    private Users user;

    @Column(name = "settlement_price")
    private Long settlementPrice;

    @Column(name = "method_of_payment")
    private String methodOfPayment;

    public PaymentLogDto toPaymentLog() {
        return PaymentLogDto.builder()
                .message(DateToString.dateToString(getCreatedDate()) + "에 " + methodOfPayment + "로 " + settlementPrice + "원을 결제하셨습니다.")
                .build();
    }
}
