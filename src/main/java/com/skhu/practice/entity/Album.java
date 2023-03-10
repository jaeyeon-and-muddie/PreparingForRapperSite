package com.skhu.practice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skhu.practice.dto.AlbumResponseDto;
import com.skhu.practice.entity.base.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
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

    @OneToMany(mappedBy = "album")
    @ToString.Exclude
    @JsonIgnore
    private List<AlbumSong> songsInAlbum;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id") // 추후에 artist 도 등록할 수 있으면, 그 때 추가할 것임
    private Users artist;

    @Column(columnDefinition = "LONGTEXT", name = "INTRODUCTION")
    private String introduction;

    @Column(name = "AVERAGE_OF_STAR")
    private Double averageOfStar;

    @Column(name = "NUMBER_OF_REVIEW")
    private Long numberOfReview;

    @Column(name = "HITS")
    private Long hits;

    @Column(name = "image")
    private String image;

    public AlbumResponseDto toResponseDto() {
        return AlbumResponseDto.builder()
                .id(this.id)
                .name(this.name)
                .artist(artist.toResponseDto())
                .dateOfIssue(this.dateOfIssue)
                .songsInAlbum(this.songsInAlbum
                        .stream()
                        .map(AlbumSong::toResponseDto)
                        .collect(Collectors.toList()))
                .averageOfStar(averageOfStarFormatter(averageOfStar))
                .numberOfReview(this.numberOfReview)
                .hits(this.hits)
                .introduction(this.introduction)
                .image(this.image)
                .build();
    }

    private String averageOfStarFormatter(Double averageOfStar) {
        String format = "%.0f";

        for (int digit = 1; digit <= 2; digit++) {
            averageOfStar *= 10;
            if (averageOfStar % 10 != 0) {
                format = "%." + digit + "f";
            }
        }

        return String.format(format, averageOfStar / 100);
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

        if (this.image == null || this.image.isEmpty() || this.image.isBlank()) {
            this.image = "https://i.ytimg.com/vi/dlqLwq8XTeU/maxresdefault.jpg";
        }
    }

    public void visit() {
        this.hits++;
    }

    public void turnBackHits() { // Comment 를 작성할 때, redirection 으로 인한 조회수 상승을 일시적으로 막을 방편 (이건 무조건 일시적이여야 할 메소드이다)
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
