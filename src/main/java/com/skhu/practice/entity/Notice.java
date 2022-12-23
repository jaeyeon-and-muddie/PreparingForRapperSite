package com.skhu.practice.entity;

import com.skhu.practice.entity.base.BaseEntity;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@Table(name = "NOTICE") // Table = User 로 설정
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "HITS")
    private Long hits;

    @Column(name = "AUTHOR") // 작성자
    private String author;

    @Column(columnDefinition = "LONGTEXT", name = "content")
    private String content;
}
