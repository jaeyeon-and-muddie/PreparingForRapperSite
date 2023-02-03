package com.skhu.practice.repository;

import com.skhu.practice.entity.AlbumNestedComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumNestedCommentRepository extends JpaRepository<AlbumNestedComment, Long> {

}
