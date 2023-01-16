package com.skhu.practice.Repository;

import com.skhu.practice.Entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findByTitle(String albumTitle);
}
