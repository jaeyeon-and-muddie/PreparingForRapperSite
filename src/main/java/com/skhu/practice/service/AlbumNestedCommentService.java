package com.skhu.practice.service;

import com.skhu.practice.entity.AlbumNestedComment;
import com.skhu.practice.repository.AlbumCommentRepository;
import com.skhu.practice.repository.AlbumNestedCommentRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AlbumNestedCommentService {

    private final AlbumNestedCommentRepository albumNestedCommentRepository;
    private final UserRepository userRepository;
    private final AlbumCommentRepository albumCommentRepository;

    public void save(Long commentId, String content, String username) {
        albumNestedCommentRepository.save(AlbumNestedComment.builder()
                .comment(albumCommentRepository.findById(commentId).orElseThrow(NoSuchElementException::new))
                .author(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new))
                .content(content)
                .build());
    }
}
