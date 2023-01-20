package com.skhu.practice.service;

import com.skhu.practice.dto.AlbumRequestDto;
import com.skhu.practice.dto.AlbumResponseDto;
import com.skhu.practice.entity.Album;
import com.skhu.practice.entity.AlbumReview;
import com.skhu.practice.repository.AlbumRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;

    public boolean save(AlbumRequestDto albumRequestDto, String username) { // 여기에 validate 코드를 집어넣어서, 잘 못 입력한 경우 다시 redirection 할 수 있도록
        if (albumRequestDto.isNotIllegal()) { // isNotIllegal 할 때에만 하잖아
            albumRepository.save(Album.builder()
                    .name(albumRequestDto.getName())
                    .artist(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new))
                    .image(albumRequestDto.getImage())
                    .songsInAlbum(albumRequestDto.extractSongsInAlbum())
                    .dateOfIssue(albumRequestDto.getDateOfIssue())
                    .introduction(albumRequestDto.getIntroduction()).build());
            return true;
        }

        return false;
    }

    public List<AlbumResponseDto> findAll() {
        return albumRepository.findAll()
                .stream()
                .map(Album::toResponseDto)
                .collect(Collectors.toList());
    }

    public AlbumResponseDto findById(Long id) {
        return albumRepository.findById(id)
                .orElseThrow(NoSuchElementException::new)
                .toResponseDto();
    }

    public AlbumResponseDto albumDetail(Long id) { // 조회수 처리도 추가적으로
        Album album = albumRepository.findById(id).orElseThrow(NoSuchElementException::new);
        album.visit();
        album = albumRepository.save(album);

        return album.toResponseDto();
    }

    public List<AlbumResponseDto> findByMonthlyAlbum() {
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.of(endDate.getYear(), endDate.getMonthValue(), 1);
        return albumRepository.findByDateOfIssueBetweenOrderByDateOfIssueAsc(startDate, endDate)
                .stream()
                .map(Album::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<AlbumResponseDto> findTop5ByAverageOfStar() {
        return albumRepository.findTop5ByOrderByAverageOfStarDesc()
                .stream()
                .map(Album::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<AlbumResponseDto> findTop5ByNumberOfReview() {
        return albumRepository.findTop5ByOrderByNumberOfReviewDesc()
                .stream()
                .map(Album::toResponseDto)
                .collect(Collectors.toList());
    }

    public List<AlbumResponseDto> findTop5ByHits() {
        return albumRepository.findTop5ByOrderByHitsDesc()
                .stream()
                .map(Album::toResponseDto)
                .collect(Collectors.toList());
    }
}
