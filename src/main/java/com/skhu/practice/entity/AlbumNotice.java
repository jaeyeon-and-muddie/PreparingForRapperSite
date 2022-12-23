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
import java.time.LocalDateTime;

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

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "USER_ID")
    private User author;

    @Column(columnDefinition = "LONGTEXT", name = "CONTENT")
    private String content;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name = "ALBUM_ID")
    private Album album;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    @Builder
    public AlbumNotice(Long hits, User author, String content, Album album) {
        this.hits = hits;
        this.author = author;
        this.content = content;
        this.album = album;
    }

    public void visit() {
        this.hits++;
    }
}
