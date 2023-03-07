package com.skhu.practice.Entity.market;

import com.skhu.practice.Entity.Base.BaseEntity;
import com.skhu.practice.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "basket")
public class Basket extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="userId")
    private User user;

    @ManyToOne
    @JoinColumn(name="merchandiseId")
    private Merchandise merchandise;

    @Column(name="NUM")
    private Long num;
}
