package com.skhu.practice.Entity.mixtape;

import com.skhu.practice.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(columnDefinition = "LONGTEXT", name="PRE_TAG")
    private String preTag;

    @Column(name="RELEASE_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;

    //이미지는 이미지를 업로드 해야하는 문제가 잇음.
}
