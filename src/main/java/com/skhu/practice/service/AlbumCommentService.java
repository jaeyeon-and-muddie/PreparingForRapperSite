package com.skhu.practice.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.skhu.practice.dto.AlbumCommentResponseDto;
import com.skhu.practice.entity.Album;
import com.skhu.practice.entity.AlbumComment;
import com.skhu.practice.entity.Users;
import com.skhu.practice.repository.AlbumCommentRepository;
import com.skhu.practice.repository.AlbumRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumCommentService {

    private final AlbumCommentRepository albumCommentRepository;
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;

    public void save(Long id, String content, String username) {
        Users users = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        Album album = albumRepository.findById(id).orElseThrow(NoSuchElementException::new);
        album.turnBackHits();
        albumRepository.save(album);

        albumCommentRepository.save(AlbumComment.builder()
                .author(users)
                .album(album)
                .content(content)
                .build());
    }

    public List<AlbumCommentResponseDto> findAllCommentByAlbum(Long id) {
        return albumCommentRepository.findAllByAlbumId(id)
                .stream()
                .map(AlbumComment::toResponseDto)
                .collect(Collectors.toList());
    }
}
