package com.skhu.practice.service;

import com.skhu.practice.entity.MixTapeComment;
import com.skhu.practice.entity.MixTapeNestedComment;
import com.skhu.practice.repository.MixTapeCommentRepository;
import com.skhu.practice.repository.MixTapeNestedCommentRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class MixTapeNestedCommentService {

    private final MixTapeNestedCommentRepository mixTapeNestedCommentRepository;
    private final UserRepository userRepository;
    private final MixTapeCommentRepository mixTapeCommentRepository;
    private final AlarmService alarmService;


    public void save(Long commentId, String content, String username) {
        MixTapeComment comment = mixTapeCommentRepository.findById(commentId).orElseThrow(NoSuchElementException::new);
        alarmService.saveAlarmWhenTagContainMessage(content, "mixtape/detail/" + comment.getMixTape().getId(), username);

        mixTapeNestedCommentRepository.save(MixTapeNestedComment.builder()
                .comment(comment)
                .author(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new))
                .content(content)
                .build());
    }
}
