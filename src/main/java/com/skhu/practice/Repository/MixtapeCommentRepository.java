package com.skhu.practice.Repository;

import com.skhu.practice.Entity.mixtape.MixtapeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MixtapeCommentRepository extends JpaRepository<MixtapeComment, Long> {
}