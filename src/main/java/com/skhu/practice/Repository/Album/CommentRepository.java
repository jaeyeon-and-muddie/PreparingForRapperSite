package com.skhu.practice.Repository.Album;

import com.skhu.practice.Entity.album.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}