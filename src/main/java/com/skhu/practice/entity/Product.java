package com.skhu.practice.entity;

import com.skhu.practice.dto.ProductResponseDto;
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
import javax.persistence.PrePersist;
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

    @Column(name = "image", columnDefinition = "LONGTEXT")
    private String image;

    @OneToOne
    @JoinColumn(name = "registrant")
    private Users registrant;

    @PrePersist
    private void prePersist() {
        if (this.image == null || this.image.isEmpty() || this.image.isBlank()) {
            this.image = "https://pbs.twimg.com/media/DaAVqr_UQAAgeMC.jpg";
        }
    }

    public ProductResponseDto toResponseDto() {
        return ProductResponseDto.builder()
                .id(this.id)
                .stock(this.stock)
                .name(this.name)
                .explain(this.explain)
                .price(this.price)
                .registrant(this.registrant.toResponseDto())
                .image(this.image)
                .createdDate(getCreatedDate().toLocalDate())
                .build();
    }
}
