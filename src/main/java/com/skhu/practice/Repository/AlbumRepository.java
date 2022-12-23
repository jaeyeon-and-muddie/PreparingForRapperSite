package com.skhu.practice.Repository;

import com.skhu.practice.DTO.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Integer> {
}
