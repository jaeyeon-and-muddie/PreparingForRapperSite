package com.skhu.practice.repository;

import com.skhu.practice.entity.AlbumSong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumSongRepository extends JpaRepository<AlbumSong, Long> {
}
