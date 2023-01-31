package com.skhu.practice.repository;

import com.skhu.practice.entity.JudgeMixTapeSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JudgeMixTapeSongRepository extends JpaRepository<JudgeMixTapeSong, Long> {
}
