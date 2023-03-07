package com.skhu.practice.Repository.Album;

import com.skhu.practice.Entity.album.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findByArtistName(String artistName);
}
