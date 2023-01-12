package com.skhu.practice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skhu.practice.dto.AlbumCommentResponseDto;
import com.skhu.practice.entity.base.Comment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@ToString
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ALBUM_COMMENT")
public class AlbumComment extends Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name = "ALBUM")
    private Album album;

    @OneToMany(mappedBy = "comment")
    @JsonIgnore
    private List<AlbumNestedComment> nestedComments;

    public AlbumCommentResponseDto toResponseDto() {
        return AlbumCommentResponseDto.builder()
                .id(this.id)
                .author(getAuthor().toResponseDto())
                .album(this.album.toResponseDto())
                .content(getContent())
                .isModified(getIsModified())
                .createdDate(getCreatedDate())
                .build();
    }
}
