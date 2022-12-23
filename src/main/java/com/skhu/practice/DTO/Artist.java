package com.skhu.practice.DTO;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Artist {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    private String artistName;

    @Column
    private String Genre;

    @OneToMany(mappedBy="artist")
    List<Album> Albums;


}
