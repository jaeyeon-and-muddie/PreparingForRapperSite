package com.skhu.practice.entity;

import com.skhu.practice.entity.base.BaseEntity;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "stock")
    private Long stock;

    @Column(name = "name")
    private String name;

    @Column(name = "explain", columnDefinition = "LONGTEXT")
    private String explain;

    @Column(name = "price")
    private Long price;

    @OneToOne
    @JoinColumn(name = "registrant")
    private Users registrant;
}
