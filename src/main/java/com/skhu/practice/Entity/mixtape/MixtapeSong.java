package com.skhu.practice.Entity.mixtape;


import com.skhu.practice.Entity.Album;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MixtapeSong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MIXTAPE_SONG_ID")
    private long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="NUMBER")
    private int number;

    @ManyToOne
    @JoinColumn(name="mixtapeId")
    private Mixtape mixtape;

    @Column(name="PARTICIPANTS")
    private String participants;

}
