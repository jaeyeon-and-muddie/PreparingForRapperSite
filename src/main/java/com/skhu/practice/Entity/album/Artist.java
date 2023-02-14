package com.skhu.practice.Entity.album;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="ARTIST_ID")
    private long id;

    @Column(name="ARTIST_NAME")
    private String artistName;

    @Column(name="GENRE")
    private String Genre;

    @OneToMany(mappedBy="artist")
    List<Album> Albums;


}
