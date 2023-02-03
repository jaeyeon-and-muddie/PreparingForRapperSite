package com.skhu.practice.entity;

import com.skhu.practice.dto.AlbumSongResponseDto;
import com.skhu.practice.dto.MixTapeSongResponseDto;
import com.skhu.practice.entity.base.Song;
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

@Entity
@SuperBuilder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class JudgeMixTapeSong extends Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = JudgeMixTape.class)
    @JoinColumn(name = "judge_mix_tape")
    private JudgeMixTape judgeMixTape;
}