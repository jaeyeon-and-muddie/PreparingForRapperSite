package com.skhu.practice.entity;

import com.skhu.practice.dto.AlbumCommentResponseDto;
import com.skhu.practice.entity.base.BaseEntity;
import com.skhu.practice.repository.AlbumCommentRepository;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ALBUM_COMMENT")
public class AlbumComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "USERS_ID")
    private Users author;

    @ManyToOne(targetEntity = Album.class)
    @JoinColumn(name = "ALBUM")
    private Album album;

    @Column(columnDefinition = "LONGTEXT", name = "CONTENT")
    private String content;

    @Column(name = "IS_MODIFIED")
    private Boolean isModified;

    @Builder
    public AlbumComment(Users author, Album album, String content, Boolean isModified) {
        this.author = author;
        this.album = album;
        this.content = content;
        this.isModified = isModified;
    }

    @PrePersist
    public void prePersist() {
        if (isModified == null) {
            isModified = false;
        }
    }

    public AlbumCommentResponseDto toResponseDto() {
        return AlbumCommentResponseDto.builder()
                .id(this.id)
                .author(this.author.toResponseDto())
                .album(this.album.toResponseDto())
                .content(this.content)
                .isModified(this.isModified)
                .createdDate(getCreatedDate())
                .build();
    }
}
