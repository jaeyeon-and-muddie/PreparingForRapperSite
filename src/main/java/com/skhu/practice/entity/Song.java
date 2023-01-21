package com.skhu.practice.entity;

import com.skhu.practice.dto.SongResponseDto;
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

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name = "album")
    private Album album;

    @Column(columnDefinition = "LONGTEXT")
    private String lyric;

    public SongResponseDto toResponseDto() {
        return SongResponseDto.builder()
                .id(this.id)
                .title(this.title)
                .lyric(this.lyric)
                .build();
    }
}
