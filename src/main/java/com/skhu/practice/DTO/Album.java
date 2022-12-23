package com.skhu.practice.DTO;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Album {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id; //long으로 바꿔주는게 훨좋을듯
    @ManyToOne
    @JoinColumn(name="artistId")
    private Artist artist;
    @Column
    private String title;
    @Column
    private int numberOfSongs;
    @Column
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;
}
