package com.skhu.practice.entity;

import com.skhu.practice.dto.AlbumResponseDto;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ALBUM")
public class Album extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DATE_OF_ISSUE")
    private LocalDate dateOfIssue;

    @ElementCollection
    @CollectionTable(
            name = "SONG",
            joinColumns = @JoinColumn(name = "ID")
    )
    @Column(name = "SONGS_IN_ALBUM")
    private List<String> songsInAlbum;

    @Column(name = "ARTIST_NAME") // 추후에 artist 도 등록할 수 있으면, 그 때 추가할 것임
    private String artistName;

    @Column(columnDefinition = "LONGTEXT", name = "INTRODUCTION")
    private String introduction;

    @Column(name = "AVERAGE_OF_STAR")
    private Double averageOfStar;

    @Column(name = "NUMBER_OF_REVIEW")
    private Long numberOfReview;

    @Column(name = "HITS")
    private Long hits;

    @Builder
    public Album(String name, LocalDate dateOfIssue, List<String> songsInAlbum, String artistName,
                 String introduction, Double averageOfStar, Long numberOfReview, Long hits) {
        this.name = name;
        this.dateOfIssue = dateOfIssue;
        this.songsInAlbum = songsInAlbum;
        this.artistName = artistName;
        this.introduction = introduction;
        this.averageOfStar = averageOfStar;
        this.numberOfReview = numberOfReview;
        this.hits = hits;
    }

    public AlbumResponseDto toResponseDto() {
        return AlbumResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .artistName(this.artistName)
                .dateOfIssue(this.dateOfIssue)
                .songsInAlbum(this.songsInAlbum)
                .averageOfStar(this.averageOfStar)
                .numberOfReview(this.numberOfReview)
                .hits(this.hits)
                .introduction(this.introduction)
                .build();
    }

    @PrePersist // Entity Persist 이전에 해당 어노테이션이 붙어있으면 실행됨
    private void prePersist() {
        if (this.averageOfStar == null) {
            this.averageOfStar = 0D;
        }

        if (this.numberOfReview == null) {
            this.numberOfReview = 0L;
        }

        if (this.hits == null) {
            this.hits = 0L;
        }
    }

    public void visit() {
        this.hits++;
    }

    public void turnBackHits() { // Comment 를 작성할 때, redirection 으로 인한 조회수 상승을 일시적으로 막을 방편
        this.hits--;
    }

    public void reviewForThisAlbum(Double star) { // 이 때 외부적으로 받아야 하는 것은 star 뿐임
        // 여기서 star 를 가지고 식을 진행해야함
        double totalOfGivenStarThisAlbum = this.averageOfStar * this.numberOfReview;
        totalOfGivenStarThisAlbum += star;
        this.numberOfReview++;
        this.averageOfStar = totalOfGivenStarThisAlbum / this.numberOfReview;
    }
}
