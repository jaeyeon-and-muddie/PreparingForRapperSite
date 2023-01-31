package com.skhu.practice.entity;

import com.skhu.practice.repository.JudgeMixTapeRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "emote")
public class Emote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_good")
    private Boolean isGood;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(targetEntity = JudgeMixTape.class)
    @JoinColumn(name = "judge_mix_tape_id")
    private JudgeMixTape judgeMixTape;
}
