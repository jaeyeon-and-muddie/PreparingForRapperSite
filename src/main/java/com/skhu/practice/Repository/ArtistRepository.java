package com.skhu.practice.Repository;

import com.skhu.practice.Entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Integer> {
    Artist findByArtistName(String artistName);
}
