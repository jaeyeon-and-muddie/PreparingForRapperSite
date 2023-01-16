package com.skhu.practice.Repository;

import com.skhu.practice.Entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    List<Song> findAllByAlbumId(Long albumId);
}
