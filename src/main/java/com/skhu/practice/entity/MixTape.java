package com.skhu.practice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skhu.practice.dto.AlbumResponseDto;
import com.skhu.practice.dto.MixTapeResponseDto;
import com.skhu.practice.entity.base.BaseEntity;
import com.skhu.practice.entity.base.Mix;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "mix_tape")
public class MixTape extends Mix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "mixTape")
    @ToString.Exclude
    @JsonIgnore
    private List<MixTapeSong> songsInMixTape;

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
    }

    public MixTapeResponseDto toResponseDto() {
        return MixTapeResponseDto.builder()
                .id(this.id)
                .name(getName())
                .artist(this.artist.toResponseDto())
                .dateOfIssue(getDateOfIssue())
                .soundCloud(getSoundCloud())
                .songsInMixTape(this.songsInMixTape
                        .stream()
                        .map(MixTapeSong::toResponseDto)
                        .collect(Collectors.toList()))
                .averageOfStar(averageOfStarFormatter(getAverageOfStar()))
                .numberOfReview(getNumberOfReview())
                .hits(getHits())
                .introduction(getIntroduction())
                .image(getImage())
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
}
