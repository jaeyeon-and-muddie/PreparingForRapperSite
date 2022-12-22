package com.skhu.practice.entity;

import com.skhu.practice.entity.base.BaseEntity;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@ToString
@Table(name = "ALBUM")
public class Album extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "DATE_OF_ISSUE")
    private LocalDate dateOfIssue;

    @ElementCollection
    @CollectionTable(
            name = "song",
            joinColumns = @JoinColumn(name = "ID")
    )
    @Column(name = "SONGS_IN_ALBUM")
    private Set<String> songsInAlbum;

    @Column(name = "ARTIST_NAME")
    private String artistName;
}