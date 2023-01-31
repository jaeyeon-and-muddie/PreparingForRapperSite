package com.skhu.practice.entity;

import com.skhu.practice.dto.AlbumReviewResponseDto;
import com.skhu.practice.entity.base.Review;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@ToString
@AllArgsConstructor
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ALBUM_REVIEW") // Table = User 로 설정
public class AlbumReview extends Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id; // 이게 postNumber 임

    @ElementCollection
    @CollectionTable(
            name = "ALBUM_REVIEW_OF_SONG",
            joinColumns = @JoinColumn(name = "ID")
    )
    @Column(name = "ALBUM_REVIEW_OF_SONGS")
    private List<String> reviewOfSongs;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name = "ALBUM_ID")
    private Album album;

    @Column(name = "STAR", nullable = false)
    private Double star;

    public void modified(String title, List<String> reviewOfSongs, Double star) {
        super.modified(title);
        this.reviewOfSongs = reviewOfSongs;
        this.star = star;
    }

    public AlbumReviewResponseDto toResponseDto() {
        return AlbumReviewResponseDto.builder()
                .id(this.id)
                .hits(getHits())
                .author(getAuthor().toResponseDto())
                .reviewOfSongs(this.reviewOfSongs)
                .album(this.album.toResponseDto())
                .star(this.star)
                .updateTime(getUpdateTime())
                .title(getTitle())
                .createdDate(this.getCreatedDate().toLocalDate())
                .build();
    }
}
