package com.skhu.practice.repository;

import com.skhu.practice.entity.MixTape;
import com.skhu.practice.entity.MixTapeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MixTapeRepository extends JpaRepository<MixTape, Long> {
}
