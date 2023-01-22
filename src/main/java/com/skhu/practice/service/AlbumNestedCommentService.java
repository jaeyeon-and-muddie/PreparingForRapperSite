package com.skhu.practice.service;

import com.skhu.practice.entity.AlbumComment;
import com.skhu.practice.entity.AlbumNestedComment;
import com.skhu.practice.entity.base.Comment;
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
    private final AlarmService alarmService;

    public void save(Long commentId, String content, String username) {
        AlbumComment comment = albumCommentRepository.findById(commentId).orElseThrow(NoSuchElementException::new);
        alarmService.saveAlarmWhenTagContainMessage(content, "album/detail/" + comment.getAlbum().getId(), username);

        albumNestedCommentRepository.save(AlbumNestedComment.builder()
                .comment(comment)
                .author(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new))
                .content(content)
                .build());
    }
}
