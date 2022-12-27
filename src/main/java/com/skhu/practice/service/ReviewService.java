package com.skhu.practice.service;

import com.skhu.practice.dto.albumnotice.AlbumNoticePostResponseDto;
import com.skhu.practice.dto.albumnotice.AlbumNoticeRequestDto;
import com.skhu.practice.dto.albumnotice.AlbumNoticeReviewResponseDto;
import com.skhu.practice.entity.Album;
import com.skhu.practice.entity.AlbumNotice;
import com.skhu.practice.entity.Users;
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

    public void saveAlbumAndNotice(Users users, AlbumNoticeRequestDto albumNoticeRequestDto) {
        Album album = Album.builder()
                .name(albumNoticeRequestDto.getAlbumName())
                .artistName(albumNoticeRequestDto.getArtistName())
                .dateOfIssue(albumNoticeRequestDto.getDateOfIssue())
                .songsInAlbum(albumNoticeRequestDto.wrapSongsInAlbum())
                .build();
        album = albumRepository.save(album); // 이 과정에서, 사실 Album 이 원래 존재하고 해야하는건데, 여기서 생성했으면 안됐음, 근데 일단은 그렇게 해놓자고

        AlbumNotice albumNotice = AlbumNotice.builder()
                .album(album)
                .author(users)
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
                .postNumber(id)
                .albumName(albumNotice.getAlbum().getName())
                .author(albumNotice.getAuthor())
                .hits(albumNotice.getHits())
                .content(albumNotice.getContent())
                .songsInAlbum(albumNotice.getAlbum().getSongsInAlbum())
                .modifiedTime(albumNotice.getUpdateTime())
                .artistName(albumNotice.getAlbum().getArtistName())
                .dateOfIssue(albumNotice.getAlbum().getDateOfIssue())
                .build();
    }

    public String getContentByPostNumber(Long id) {
        return albumNoticeRepository.findById(id)
                .orElseThrow(NoSuchElementException::new).getContent();
    }

    public void update(Long id, String content) {
        AlbumNotice albumNotice = albumNoticeRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        albumNotice.modified(content); // update 시간 조정, 그리고 만일 앨범에 대한 정보를 수정하였어도, album 이 변경되어야지, 다른게 변경되는 것이 아니다.
        albumNoticeRepository.save(albumNotice);
    }

    public void delete(Long id) {
        AlbumNotice albumNotice = albumNoticeRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        albumNoticeRepository.delete(albumNotice);
    }
}
