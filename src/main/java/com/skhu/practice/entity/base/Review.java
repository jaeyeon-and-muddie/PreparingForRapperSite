package com.skhu.practice.entity.base;

import com.skhu.practice.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.time.LocalDateTime;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@MappedSuperclass
public class Review extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "hits")
    private Long hits;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "users_id")
    private Users author;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    @PrePersist
    public void prePersist() {
        if (hits == null) {
            this.hits = 0L;
        }

        if (updateTime == null) {
            this.updateTime = LocalDateTime.now();
        }
    }

    public void visit() { // 방문하였을 때
        this.hits++;
    }

    public void modified(String title) {
        this.title = title;
        updateTime = LocalDateTime.now();
    }

}