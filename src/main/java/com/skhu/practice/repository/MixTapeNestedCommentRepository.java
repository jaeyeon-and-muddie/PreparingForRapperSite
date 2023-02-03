package com.skhu.practice.repository;

import com.skhu.practice.entity.MixTapeComment;
import com.skhu.practice.entity.MixTapeNestedComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MixTapeNestedCommentRepository extends JpaRepository<MixTapeNestedComment, Long> {
}
