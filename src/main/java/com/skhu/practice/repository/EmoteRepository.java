package com.skhu.practice.repository;

import com.skhu.practice.entity.Emote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmoteRepository extends JpaRepository<Emote, Long> {

    boolean existsByUserIdAndJudgeMixTapeId(Long userId, Long judgeMixTapeId);

    Optional<Emote> findByUserIdAndJudgeMixTapeId(Long userId, Long judgeMixTapeId);
}
