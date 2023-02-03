package com.skhu.practice.service;

import com.skhu.practice.dto.JudgeMixTapeResponseDto;
import com.skhu.practice.dto.MixTapeRequestDto;
import com.skhu.practice.dto.SongRequestDto;
import com.skhu.practice.entity.JudgeMixTape;
import com.skhu.practice.entity.JudgeMixTapeSong;
import com.skhu.practice.repository.JudgeMixTapeRepository;
import com.skhu.practice.repository.JudgeMixTapeSongRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JudgeMixTapeService {

    private final JudgeMixTapeRepository judgeMixTapeRepository;

    private final UserRepository userRepository;

    private final JudgeMixTapeSongRepository judgeMixTapeSongRepository;

    @Scheduled(cron = "0 0 9 * * *", zone = "Asia/Seoul")
    public void expire() {
        List<JudgeMixTape> judgeMixTapes = judgeMixTapeRepository.findAll();

        for (JudgeMixTape mixTape : judgeMixTapes) {
            if (mixTape.isExpire(LocalDateTime.now())) {
                judgeMixTapeRepository.delete(mixTape);
            }
        }
    }

    public List<JudgeMixTapeResponseDto> findAll() {
        return judgeMixTapeRepository.findAll()
                .stream()
                .map(JudgeMixTape::toResponseDto)
                .collect(Collectors.toList());
    }

    public void save(MixTapeRequestDto mixTapeRequestDto, List<SongRequestDto> songs, String username) {
        int SEVEN_DAYS = 7;
        JudgeMixTape judgeMixTape = JudgeMixTape.builder()
                .name(mixTapeRequestDto.getName())
                .artist(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new))
                .image(mixTapeRequestDto.getImage())
                .dateOfIssue(mixTapeRequestDto.getDateOfIssue())
                .introduction(mixTapeRequestDto.getIntroduction())
                .soundCloud(mixTapeRequestDto.getSoundCloud()).build();
        judgeMixTape.decideExpireDate(LocalDateTime.now(), SEVEN_DAYS);
        saveSongs(judgeMixTapeRepository.save(judgeMixTape), songs);
    }

    public void saveSongs(JudgeMixTape judgeMixTape, List<SongRequestDto> songs) { // 이 save 부분은 많이 고민해봐야할 듯
        for (SongRequestDto song : songs) {
            judgeMixTapeSongRepository.save(JudgeMixTapeSong.builder()
                    .judgeMixTape(judgeMixTape)
                    .lyric(song.getLyric())
                    .title(song.getTitle())
                    .build());
        }
    }
}
