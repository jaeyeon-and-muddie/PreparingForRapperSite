package com.skhu.practice.Entity.mixtape;

import com.skhu.practice.Entity.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Mixtape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MIXTAPE_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(name="TITLE")
    private String title;

    @Column(name="NUMBER_OF_SONGS")
    private int numberOfSongs;

    @Column(name="INTRODUCTION")
    private String introduction;




}
