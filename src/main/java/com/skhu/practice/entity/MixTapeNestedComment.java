package com.skhu.practice.entity;

import com.skhu.practice.dto.AlbumNestedCommentResponseDto;
import com.skhu.practice.dto.MixTapeNestedCommentResponseDto;
import com.skhu.practice.entity.base.Comment;
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
import javax.persistence.Table;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "mix_tape_nested_comment")
public class MixTapeNestedComment extends Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = MixTapeComment.class)
    @JoinColumn(name = "nested_comment")
    private MixTapeComment comment;

    public MixTapeNestedCommentResponseDto toResponseDto() {
        return MixTapeNestedCommentResponseDto.builder()
                .id(this.id)
                .author(getAuthor().toResponseDto())
                .content(getContent())
                .isModified(getIsModified())
                .createdDate(getCreatedDate())
                .build();
    }
}
