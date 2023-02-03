package com.skhu.practice.repository;

import com.skhu.practice.entity.MixTapeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MixTapeCommentRepository extends JpaRepository<MixTapeComment, Long> {

    List<MixTapeComment> findAllByMixTapeId(Long mixTapeId);
}
