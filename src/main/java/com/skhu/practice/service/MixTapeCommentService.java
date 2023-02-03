package com.skhu.practice.service;

import com.skhu.practice.dto.AlbumCommentResponseDto;
import com.skhu.practice.dto.MixTapeCommentResponseDto;
import com.skhu.practice.entity.Album;
import com.skhu.practice.entity.AlbumComment;
import com.skhu.practice.entity.MixTape;
import com.skhu.practice.entity.MixTapeComment;
import com.skhu.practice.entity.Users;
import com.skhu.practice.repository.MixTapeCommentRepository;
import com.skhu.practice.repository.MixTapeRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MixTapeCommentService {

    private final MixTapeCommentRepository mixTapeCommentRepository;
    private final UserRepository userRepository;
    private final MixTapeRepository mixTapeRepository;
    private final AlarmService alarmService;

    public void save(Long id, String content, String username) {
        Users users = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        MixTape mixTape = mixTapeRepository.findById(id).orElseThrow(NoSuchElementException::new);
        mixTape.turnBackHits();
        mixTapeRepository.save(mixTape);
        alarmService.saveAlarmWhenTagContainMessage(content, "mixtape/detail/" + id, username);

        mixTapeCommentRepository.save(MixTapeComment.builder()
                .author(users)
                .mixTape(mixTape)
                .content(content)
                .build());
    }

    public List<MixTapeCommentResponseDto> findAllCommentByAlbum(Long id) {
        return mixTapeCommentRepository.findAllByMixTapeId(id)
                .stream()
                .map(MixTapeComment::toResponseDto)
                .collect(Collectors.toList()); // 여기서 AlbumNestedCommnet 도 가져와야하는데, 가져옴
    }
}
