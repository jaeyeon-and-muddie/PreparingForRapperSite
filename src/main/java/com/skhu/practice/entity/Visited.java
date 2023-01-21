package com.skhu.practice.entity;

import com.skhu.practice.dto.VisitedResponseDto;
import lombok.AllArgsConstructor;
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

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Visited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "users")
    private Users users;

    @Override
    public boolean equals(Object object) {
        return object instanceof Visited
                && ((Visited) object).title.equals(this.title);
    }

    public VisitedResponseDto toResponseDto() {
        return VisitedResponseDto.builder()
                .id(this.id)
                .url(this.url)
                .title(this.title)
                .build();
    }
}
