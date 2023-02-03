package com.skhu.practice.service;

import com.skhu.practice.dto.Judge;
import com.skhu.practice.entity.Emote;
import com.skhu.practice.entity.JudgeMixTape;
import com.skhu.practice.entity.JudgeMixTapeSong;
import com.skhu.practice.entity.MixTape;
import com.skhu.practice.entity.MixTapeSong;
import com.skhu.practice.entity.Users;
import com.skhu.practice.repository.EmoteRepository;
import com.skhu.practice.repository.JudgeMixTapeRepository;
import com.skhu.practice.repository.MixTapeRepository;
import com.skhu.practice.repository.MixTapeSongRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EmoteService {

    private final EmoteRepository emoteRepository;
    private final JudgeMixTapeRepository judgeMixTapeRepository;
    private final UserRepository userRepository;
    private final MixTapeRepository mixTapeRepository;
    private final MixTapeSongRepository mixTapeSongRepository;

    public void occurEmote(boolean isGood, String username, Long judgeMixTapeId) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(NoSuchElementException::new);
        JudgeMixTape judgeMixTape = judgeMixTapeRepository.findById(judgeMixTapeId)
                .orElseThrow(NoSuchElementException::new);
        Emote emote = emoteRepository.findByUserIdAndJudgeMixTapeId(user.getId(), judgeMixTapeId).orElse(null);

        if (emote != null) { // 있으면
            emoteRepository.delete(emote);
        }

        if (!(emote != null && emote.getIsGood() == isGood)) {
            emoteRepository.save(
                    Emote.builder()
                            .isGood(isGood)
                            .user(user)
                            .judgeMixTape(judgeMixTape)
                            .build()
            );
        }

        judgeMixTape.occurEmote(emote != null, emote == null || emote.getIsGood() != isGood, isGood);
        judge(judgeMixTape);
    }

    private void judge(JudgeMixTape judgeMixTape) { // 여기서 승격할 수 있다면 승격할 것임
        Judge resultOfJudge = judgeMixTape.judge();

        if (resultOfJudge.equals(Judge.STAY)) {
            judgeMixTapeRepository.save(judgeMixTape); // 그대로 저장
        }

        if (!resultOfJudge.equals(Judge.STAY)) {
            judgeMixTapeRepository.delete(judgeMixTape);
        }

        if (judgeMixTape.judge().equals(Judge.UP)) { // mixTape 에 승격
            saveMixTapeSong(mixTapeRepository.save(judgeMixTape.toMixTape()), judgeMixTape.getSongsInMixTape());
        }
    }

    private void saveMixTapeSong(MixTape mixTape, List<JudgeMixTapeSong> songInMixTape) {
        for (JudgeMixTapeSong song : songInMixTape) {
            mixTapeSongRepository.save(MixTapeSong.builder()
                    .title(song.getTitle())
                    .lyric(song.getLyric())
                    .mixTape(mixTape)
                    .build());
        }
    }
}
