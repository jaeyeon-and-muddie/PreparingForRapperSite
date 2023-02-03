package com.skhu.practice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skhu.practice.dto.MixTapeCommentResponseDto;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "mix_tape_comment")
public class MixTapeComment extends Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = MixTape.class)
    @JoinColumn(name = "mix_tape")
    private MixTape mixTape;

    @OneToMany(mappedBy = "comment")
    @JsonIgnore
    private List<MixTapeNestedComment> nestedComments;

    public MixTapeCommentResponseDto toResponseDto() {
        return MixTapeCommentResponseDto.builder()
                .id(this.id)
                .author(getAuthor().toResponseDto())
                .mixTape(this.mixTape.toResponseDto())
                .content(getContent())
                .isModified(getIsModified())
                .createdDate(getCreatedDate().toLocalDate())
                .mixTapeNestedComment(nestedComments.stream()
                        .map(MixTapeNestedComment::toResponseDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
