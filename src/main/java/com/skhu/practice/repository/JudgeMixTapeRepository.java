package com.skhu.practice.repository;

import com.skhu.practice.entity.JudgeMixTape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JudgeMixTapeRepository extends JpaRepository<JudgeMixTape, Long> {
}
