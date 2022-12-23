package com.skhu.practice.Sevice;


import com.skhu.practice.Repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.skhu.practice.DTO.Album;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;

    public List<Album> albumList() {

        return albumRepository.findAll();
    }
}
