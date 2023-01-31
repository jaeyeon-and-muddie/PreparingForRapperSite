package com.skhu.practice.service;

import com.skhu.practice.dto.MixTapeResponseDto;
import com.skhu.practice.entity.MixTape;
import com.skhu.practice.repository.MixTapeRepository;
import com.skhu.practice.repository.MixTapeSongRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MixTapeService {

    private final MixTapeRepository mixTapeRepository;

    public List<MixTapeResponseDto> findAll() {
        return mixTapeRepository.findAll()
                .stream()
                .map(MixTape::toResponseDto)
                .collect(Collectors.toList());
    }

    public MixTapeResponseDto mixTapeDetail(Long id) { // 조회수 처리도 추가적으로
        MixTape mixTape = mixTapeRepository.findById(id).orElseThrow(NoSuchElementException::new);
        mixTape.visit();
        mixTape = mixTapeRepository.save(mixTape);

        return mixTape.toResponseDto();
    }

    public MixTapeResponseDto findById(Long id) {
        return mixTapeRepository.findById(id)
                .orElseThrow(NoSuchElementException::new)
                .toResponseDto();
    }
}
