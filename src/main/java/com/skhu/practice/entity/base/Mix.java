package com.skhu.practice.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Mix extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    @Column(columnDefinition = "LONGTEXT", name = "introduction")
    private String introduction;

    @Column(columnDefinition = "LONGTEXT", name = "sound_cloud")
    private String soundCloud;

    @Column(name = "AVERAGE_OF_STAR")
    private Double averageOfStar;

    @Column(name = "NUMBER_OF_REVIEW")
    private Long numberOfReview;

    @Column(name = "HITS")
    private Long hits;

    @Column(name = "image")
    private String image;

    public void visit() {
        this.hits++;
    }

    public void turnBackHits() { // Comment 를 작성할 때, redirection 으로 인한 조회수 상승을 일시적으로 막을 방편 (이건 무조건 일시적이여야 할 메소드이다)
        this.hits--;
    }

    public void reviewForThisMixTape(Double star) { // 이 때 외부적으로 받아야 하는 것은 star 뿐임
        // 여기서 star 를 가지고 식을 진행해야함
        double totalOfGivenStarThisAlbum = this.averageOfStar * this.numberOfReview;
        totalOfGivenStarThisAlbum += star;
        this.numberOfReview++;
        this.averageOfStar = totalOfGivenStarThisAlbum / this.numberOfReview;
    }
}
