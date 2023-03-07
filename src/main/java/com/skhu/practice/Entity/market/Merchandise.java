package com.skhu.practice.Entity.market;

import com.skhu.practice.Entity.Base.BaseEntity;
import com.skhu.practice.Entity.User;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "merchandise")
public class Merchandise extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="merchandiseId")
    private Long id;

    @Column(name="LEFTED")
    private Long lefted;

    @Column(name="IMAGE")
    private String image;

    @Column(name="POINT")
    private Long point;

    @Column(name="NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public void minusLefted(Long num){
        this.lefted = this.lefted-num;
    }

}