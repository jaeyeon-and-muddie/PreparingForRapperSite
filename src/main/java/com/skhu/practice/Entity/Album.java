package com.skhu.practice.Entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ALBUM_ID")
    private long id; //long으로 바꿔주는게 훨좋을듯
    @ManyToOne
    @JoinColumn(name="artistId")
    private Artist artist;
    @Column(name="TITLE")
    private String title;
    @Column(name="NUMBER_OF_SONGS")
    private int numberOfSongs;
    @Column(name="INTRODUCTION")
    private String introduction;
    @OneToMany(mappedBy="album")
    List<Song> songs;
    @Column(name="RELEASE_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;
}
