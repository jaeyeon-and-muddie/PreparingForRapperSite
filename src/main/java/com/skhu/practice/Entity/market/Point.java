package com.skhu.practice.Entity.market;


import com.skhu.practice.Entity.User;
import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="point")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="POINT_ID")
    private Long id;
    @OneToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(name="POINT", columnDefinition = "long default 0", nullable = false)
    private Long point;

    @PrePersist
    private void prePersist() {
        this.point = this.point == null ? 0 : this.point;
    }


    public void plusPoint(Long point){
        this.point = this.point+point;
    }
    public void minusPoint(Long point){
        this.point = this.point-point;
    }

}
