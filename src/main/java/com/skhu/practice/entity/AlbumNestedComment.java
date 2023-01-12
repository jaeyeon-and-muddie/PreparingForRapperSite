package com.skhu.practice.entity;

import com.skhu.practice.dto.AlbumNestedCommentResponseDto;
import com.skhu.practice.entity.base.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@Entity
@Table(name = "album_nested_comment")
public class AlbumNestedComment extends Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = AlbumComment.class)
    @JoinColumn(name = "nested_comment")
    private AlbumComment comment;

    public AlbumNestedCommentResponseDto toResponseDto() {
        return AlbumNestedCommentResponseDto.builder()
                .id(this.id)
                .author(getAuthor().toResponseDto())
                .content(getContent())
                .isModified(getIsModified())
                .createdDate(getCreatedDate())
                .build();
    }
}
