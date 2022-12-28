package com.skhu.practice.entity;

import com.skhu.practice.dto.AlbumReviewResponseDto;
import com.skhu.practice.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ALBUM_REVIEW") // Table = User 로 설정
public class AlbumReview extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id; // 이게 postNumber 임

    @Column(name = "title")
    private String title;

    @Column(name = "HITS")
    private Long hits;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "USER_ID")
    private Users author;

    @ElementCollection
    @CollectionTable(
            name = "REVIEW_OF_SONG",
            joinColumns = @JoinColumn(name = "ID")
    )
    @Column(name = "REVIEW_OF_SONGS")
    private List<String> reviewOfSongs;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name = "ALBUM_ID")
    private Album album;

    @Column(name = "STAR", nullable = false)
    private Double star;

    @Column(name = "UPDATE_TIME")
    private LocalDateTime updateTime;

    @Builder
    public AlbumReview(Long hits, String title, Users author, List<String> reviewOfSongs, Album album, Double star) {
        this.hits = hits;
        this.author = author;
        this.title = title;
        this.reviewOfSongs = reviewOfSongs;
        this.album = album;
        this.star = star;
    }

    @PrePersist
    public void prePersist() {
        if (hits == null) {
            this.hits = 0L;
        }

        if (updateTime == null) {
            this.updateTime = LocalDateTime.now();
        }
    }

    public void visit() {
        this.hits++;
    }

    public void modified(String title, List<String> reviewOfSongs, Double star) {
        this.title = title;
        this.reviewOfSongs = reviewOfSongs;
        this.star = star;
        this.updateTime = LocalDateTime.now();
    }

    public AlbumReviewResponseDto toResponseDto() {
        return AlbumReviewResponseDto.builder()
                .id(this.id)
                .hits(this.hits)
                .author(this.author.toResponseDto())
                .reviewOfSongs(this.reviewOfSongs)
                .album(this.album.toResponseDto())
                .star(this.star)
                .updateTime(updateTime)
                .title(this.title)
                .createdDate(this.getCreatedDate())
                .build();
    }
}
