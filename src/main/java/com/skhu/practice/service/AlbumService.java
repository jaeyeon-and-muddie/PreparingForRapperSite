package com.skhu.practice.service;

import com.skhu.practice.dto.AlbumRequestDto;
import com.skhu.practice.dto.AlbumResponseDto;
import com.skhu.practice.dto.SongRequestDto;
import com.skhu.practice.entity.Album;
import com.skhu.practice.entity.AlbumReview;
import com.skhu.practice.entity.Song;
import com.skhu.practice.repository.AlbumRepository;
import com.skhu.practice.repository.SongRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    private final SongRepository songRepository;

    public boolean save(AlbumRequestDto albumRequestDto, List<SongRequestDto> songs, String username) { // 여기에 validate 코드를 집어넣어서, 잘 못 입력한 경우 다시 redirection 할 수 있도록
        if (albumRequestDto.isNotIllegal()) { // albumRequestDto 에서 songs 에 대한 것들을 입력해줘야함
            saveSongs(albumRepository.save(Album.builder()
                    .name(albumRequestDto.getName())
                    .artist(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new))
                    .image(albumRequestDto.getImage())
                    .dateOfIssue(albumRequestDto.getDateOfIssue())
                    .introduction(albumRequestDto.getIntroduction()).build()), songs);
            return true;
        }

        return false;
    }

    public void saveSongs(Album album, List<SongRequestDto> songs) {
        for (SongRequestDto song : songs) {
            songRepository.save(Song.builder()
                    .album(album)
                    .lyric(song.getLyric())
                    .title(song.getTitle())
                    .build());
        }
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
        return albumRepository.findByDateOfIssueBetweenOrderByDateOfIssueDesc(startDate, endDate)
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
