package com.skhu.practice.entity.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@SuperBuilder
@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Song {

    @Column(name = "title")
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String lyric;
}
