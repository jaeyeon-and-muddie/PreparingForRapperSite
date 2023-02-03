package com.skhu.practice.repository;

import java.util.List;
import com.skhu.practice.entity.AlbumComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumCommentRepository extends JpaRepository<AlbumComment, Long> {

    List<AlbumComment> findAllByAlbumId(Long id);
}
