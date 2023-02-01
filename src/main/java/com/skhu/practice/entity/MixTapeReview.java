package com.skhu.practice.entity;

import com.skhu.practice.dto.AlbumReviewResponseDto;
import com.skhu.practice.dto.MixTapeResponseDto;
import com.skhu.practice.dto.MixTapeReviewResponseDto;
import com.skhu.practice.entity.base.Review;
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
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "mix_tape_review")
public class MixTapeReview extends Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String reviewOfSong;

    @ManyToOne(targetEntity = MixTape.class)
    @JoinColumn(name = "mixtape_id")
    private MixTape mixTape;

    @Column(name = "STAR", nullable = false)
    private Double star;

    public void modified(String title, String reviewOfSong, Double star) {
        super.modified(title);
        this.reviewOfSong = reviewOfSong;
        this.star = star;
    }

    public MixTapeReviewResponseDto toResponseDto() {
        return MixTapeReviewResponseDto.builder()
                .id(this.id)
                .hits(getHits())
                .author(getAuthor().toResponseDto())
                .reviewOfSong(this.reviewOfSong)
                .mixTape(this.mixTape.toResponseDto())
                .star(this.star)
                .updateTime(getUpdateTime())
                .title(getTitle())
                .createdDate(this.getCreatedDate().toLocalDate())
                .build();
    }
}
