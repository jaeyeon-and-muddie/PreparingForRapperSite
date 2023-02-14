package com.skhu.practice.Repository.Mixtape;

import com.skhu.practice.Entity.mixtape.MixtapeComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MixtapeCommentRepository extends JpaRepository<MixtapeComment, Long> {
}