package com.skhu.practice.Entity.market;

import com.skhu.practice.Entity.Base.BaseEntity;
import com.skhu.practice.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PAYMENT_ID")
    private Long id;


    @Enumerated(EnumType.STRING)
    @Column(name="HOW")
    @ColumnDefault("'PAY_DEPOSIT'")
    private How how;

    @Enumerated(EnumType.STRING)
    @Column(name="WHAT")
    private What what;

    @Column(name="MONEY")
    private Long money;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
}