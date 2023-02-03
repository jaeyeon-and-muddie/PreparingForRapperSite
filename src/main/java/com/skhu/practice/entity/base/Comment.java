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

@Getter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@MappedSuperclass
public class Comment extends BaseEntity {

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "USERS_ID")
    private Users author;

    @Column(columnDefinition = "LONGTEXT", name = "CONTENT")
    private String content;

    @Column(name = "IS_MODIFIED")
    private Boolean isModified;

    @PrePersist
    public void prePersist() {
        if (isModified == null) {
            isModified = false;
        }
    }

}
