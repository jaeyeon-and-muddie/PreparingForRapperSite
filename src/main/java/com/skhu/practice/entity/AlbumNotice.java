package com.skhu.practice.entity;

import com.skhu.practice.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "NOTICE") // Table = User 로 설정
public class AlbumNotice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id; // 이게 postNumber 임

    @Column(name = "HITS")
    private Long hits;

    @Column(name = "AUTHOR") // 작성자
    private String author;

    @Column(columnDefinition = "LONGTEXT", name = "content")
    private String content;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name = "album_id")
    private Album album;

    @Builder
    public AlbumNotice(Long hits, String author, String content, Album album) {
        this.hits = hits;
        this.author = author;
        this.content = content;
        this.album = album;
    }

    public void visit() {
        this.hits++;
    }
}
