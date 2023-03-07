package com.skhu.practice.Entity.album;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="SONG_ID")
    private Long id;
    @Column(name="TITLE")
    private String title;
    @Column(name="NUMBER")
    private int number;

    @ManyToOne
    @JoinColumn(name="albumId")
    private Album album;


    @Column(name="PARTICIPANTS")
    private String participants;

}
