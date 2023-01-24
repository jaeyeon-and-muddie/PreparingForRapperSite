package com.skhu.practice.Repository;

import com.skhu.practice.Entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Optional<Artist> findByArtistName(String artistName);
}
