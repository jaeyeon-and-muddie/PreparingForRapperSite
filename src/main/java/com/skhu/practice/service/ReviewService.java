package com.skhu.practice.service;

import com.skhu.practice.dto.albumnotice.AlbumNoticePostResponseDto;
import com.skhu.practice.dto.albumnotice.AlbumNoticeRequestDto;
import com.skhu.practice.dto.albumnotice.AlbumNoticeReviewResponseDto;
import com.skhu.practice.entity.Album;
import com.skhu.practice.entity.AlbumNotice;
import com.skhu.practice.entity.User;
import com.skhu.practice.repository.AlbumRepository;
import com.skhu.practice.repository.AlbumNoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final AlbumRepository albumRepository;
    private final AlbumNoticeRepository albumNoticeRepository;

    public void saveAlbumAndNotice(User user, AlbumNoticeRequestDto albumNoticeRequestDto) {
        // 자 이제 builder 를 이용해서 생성할 것임
        Album album = Album.builder()
                .name(albumNoticeRequestDto.getAlbumName())
                .artistName(albumNoticeRequestDto.getArtistName())
                .dateOfIssue(albumNoticeRequestDto.getDateOfIssue())
                .songsInAlbum(albumNoticeRequestDto.wrapSongsInAlbum())
                .build();
        album = albumRepository.save(album);

        AlbumNotice albumNotice = AlbumNotice.builder()
                .album(album)
                .author(user)
                .hits(0L)
                .content(albumNoticeRequestDto.getContent())
                .build();
        albumNoticeRepository.save(albumNotice);
    }

    public List<AlbumNoticeReviewResponseDto> getAllReview() {
        return albumNoticeRepository.findAll()
                .stream()
                .map(albumNotice -> AlbumNoticeReviewResponseDto.builder()
                        .postNumber(albumNotice.getId())
                        .albumName(albumNotice.getAlbum().getName())
                        .artistName(albumNotice.getAlbum().getArtistName())
                        .authorName(albumNotice.getAuthor().getEmail())
                        .dateOfIssue(albumNotice.getAlbum().getDateOfIssue())
                        .hits(albumNotice.getHits())
                        .createdDate(albumNotice.getCreatedDate())
                        .numberOfSongsInAlbum((long) albumNotice.getAlbum().getSongsInAlbum().size())
                        .build()).collect(Collectors.toList());
    }

    public AlbumNoticePostResponseDto getPostByPostNumber(Long id) {
        AlbumNotice albumNotice = albumNoticeRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        albumNotice.visit(); // 방문
        albumNoticeRepository.save(albumNotice);

        return AlbumNoticePostResponseDto.builder()
                .albumName(albumNotice.getAlbum().getName())
                .author(albumNotice.getAuthor().getEmail())
                .hits(albumNotice.getHits())
                .content(albumNotice.getContent())
                .songsInAlbum(albumNotice.getAlbum().getSongsInAlbum())
                .modifiedTime(albumNotice.getModifiedDate())
                .artistName(albumNotice.getAlbum().getArtistName())
                .dateOfIssue(albumNotice.getAlbum().getDateOfIssue())
                .build();
    }
}
