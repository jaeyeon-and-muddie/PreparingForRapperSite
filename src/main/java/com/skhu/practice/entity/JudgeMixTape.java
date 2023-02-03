package com.skhu.practice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skhu.practice.dto.Judge;
import com.skhu.practice.dto.JudgeMixTapeResponseDto;
import com.skhu.practice.entity.base.Mix;
import com.skhu.practice.service.JudgeMixTapeService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
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
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Table(name = "judge_mix_tape")
public class JudgeMixTape extends Mix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "expired_date")
    private LocalDateTime expireDate;

    @Column(name = "recommend")
    private Long recommend;

    @Column(name = "un_recommend")
    private Long unRecommend;

    @OneToMany(mappedBy = "judgeMixTape", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<JudgeMixTapeSong> songsInMixTape;

    @OneToMany(mappedBy = "judgeMixTape", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Emote> emote; // cascade 옵션을 적용하기 위해 추가, Lazy Load 이니, 실제로 성능에 영향은 미치지 않을 듯

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    private Users artist;

    @PrePersist // Entity Persist 이전에 해당 어노테이션이 붙어있으면 실행됨
    private void prePersist() {
        if (getAverageOfStar() == null) {
            setAverageOfStar(0D);
        }

        if (getNumberOfReview() == null) {
            setNumberOfReview(0L);
        }

        if (getHits() == null) {
            setHits(0L);
        }

        if (getImage() == null || getImage().isEmpty() || getImage().isBlank()) {
            setImage("https://i.ytimg.com/vi/dlqLwq8XTeU/maxresdefault.jpg");
        }

        if (recommend == null) {
            this.recommend = 0L;
        }

        if (unRecommend == null) {
            this.unRecommend = 0L;
        }
    }

    public boolean isExpire(LocalDateTime pivotTime) {
        return this.expireDate.isBefore(pivotTime); // pivot time 보다 이전 시간이면 expire 임
    }

    public void decideExpireDate(LocalDateTime standard, int day) {
        this.expireDate = standard.plusDays(day);
    }

    public void occurEmote(boolean isRemove, boolean isIncrease, boolean isGood) {
        if (!isRemove) {
            emoteCalculate(isGood, 1);
        }

        if (isRemove) {
            if (isIncrease) {
                emoteCalculate(!isGood, -1);
                emoteCalculate(isGood, 1);
            }

            if (!isIncrease) {
                emoteCalculate(isGood, -1);
            }
        }
    }

    private void emoteCalculate(boolean isGood, int add) {
        if (isGood) {
            recommend += add;
        }

        if (!isGood) {
            unRecommend += add;
        }
    }

    public Judge judge() {
        int pivot = 5;

        if (this.recommend >= pivot) {
            return Judge.UP;
        }

        if (this.unRecommend >= pivot) {
            return Judge.DOWN;
        }

        return Judge.STAY;
    }

    public MixTape toMixTape() {
        return MixTape.builder()
                .name(getName())
                .dateOfIssue(getDateOfIssue())
                .introduction(getIntroduction())
                .soundCloud(getSoundCloud())
                .averageOfStar(getAverageOfStar())
                .numberOfReview(getNumberOfReview())
                .hits(getHits())
                .image(getImage())
                .artist(this.artist)
                .build();
    }

    public JudgeMixTapeResponseDto toResponseDto() {
        return JudgeMixTapeResponseDto.builder()
                .id(this.id)
                .soundCloud(getSoundCloud())
                .recommend(this.recommend)
                .unRecommend(this.unRecommend)
                .build();
    }
}
