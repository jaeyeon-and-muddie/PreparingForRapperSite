package com.skhu.practice.Sevice;


import com.skhu.practice.Repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.skhu.practice.Entity.Album;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;

    public List<Album> albumList() {

        return albumRepository.findAll();
    }
    public Album albumInform(Long albumId){
        Album album = albumRepository.findById(albumId).orElse(null);
        return album;
    }
}
