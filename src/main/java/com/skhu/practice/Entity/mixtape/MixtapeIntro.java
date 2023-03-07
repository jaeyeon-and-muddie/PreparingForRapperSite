package com.skhu.practice.Entity.mixtape;


import com.skhu.practice.Entity.Base.BaseEntity;
import com.skhu.practice.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class MixtapeIntro extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="TITLE")
    private String title;

    @Column(name="NUMBER_OF_SONGS")
    private int numberOfSongs;

    @Column(name="RELEASE_DATE")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;

    @Column(columnDefinition = "LONGTEXT", name="PRE_TAG")
    private String preTag;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    @Column(name="INTRODUCTION")
    private String introduction;

    @ElementCollection
    @CollectionTable(name="recommends" ,joinColumns=@JoinColumn(name="recommendId"))
    @Column(name="recommends")
    private List<String> recommend;

}
