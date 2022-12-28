package com.skhu.practice.service;

import com.skhu.practice.dto.AlbumReviewRequestDto;
import com.skhu.practice.dto.AlbumReviewResponseDto;
import com.skhu.practice.dto.UserSignupDto;
import com.skhu.practice.dto.albumnotice.AlbumNoticePostResponseDto;
import com.skhu.practice.dto.albumnotice.AlbumNoticeRequestDto;
import com.skhu.practice.dto.albumnotice.AlbumNoticeReviewResponseDto;
import com.skhu.practice.entity.Album;
import com.skhu.practice.entity.AlbumReview;
import com.skhu.practice.entity.Users;
import com.skhu.practice.repository.AlbumRepository;
import com.skhu.practice.repository.AlbumReviewRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlbumReviewService {

    private final AlbumRepository albumRepository;
    private final AlbumReviewRepository albumReviewRepository;
    private final UserRepository userRepository;

    public List<AlbumReviewResponseDto> findAllReviewByAlbum(Long albumId) {
        return albumReviewRepository.findByAlbumId(albumId)
                .stream()
                .map(AlbumReview::toResponseDto)
                .collect(Collectors.toList());
    }

    public void saveAlbumReview(Long albumId, AlbumReviewRequestDto albumReviewRequestDto, String username) {
        Album album = albumRepository.findById(albumId).orElseThrow(NoSuchElementException::new);
        album.reviewForThisAlbum(albumReviewRequestDto.getStar());
        albumRepository.save(album);
        albumReviewRepository.save(AlbumReview.builder()
                .album(album)
                .title(albumReviewRequestDto.getTitle())
                .author(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new))
                .reviewOfSongs(albumReviewRequestDto.getReviewOfSongs())
                .star(albumReviewRequestDto.getStar())
                .build());
    }

    public void saveAlbumAndNotice(Users users, AlbumNoticeRequestDto albumNoticeRequestDto) {
        Album album = Album.builder()
                .name(albumNoticeRequestDto.getAlbumName())
                .artistName(albumNoticeRequestDto.getArtistName())
                .dateOfIssue(albumNoticeRequestDto.getDateOfIssue())
                .songsInAlbum(albumNoticeRequestDto.wrapSongsInAlbum())
                .build();
        album = albumRepository.save(album); // 이 과정에서, 사실 Album 이 원래 존재하고 해야하는건데, 여기서 생성했으면 안됐음, 근데 일단은 그렇게 해놓자고

        AlbumReview albumReview = AlbumReview.builder()
                .album(album)
                .author(users)
                .hits(0L)
                .build();
        albumReviewRepository.save(albumReview);
    }

    public List<AlbumNoticeReviewResponseDto> getAllReview() {
        return albumReviewRepository.findAll()
                .stream()
                .map(albumReview -> AlbumNoticeReviewResponseDto.builder()
                        .postNumber(albumReview.getId())
                        .albumName(albumReview.getAlbum().getName())
                        .artistName(albumReview.getAlbum().getArtistName())
                        .authorName(albumReview.getAuthor().getEmail())
                        .dateOfIssue(albumReview.getAlbum().getDateOfIssue())
                        .hits(albumReview.getHits())
                        .createdDate(albumReview.getCreatedDate())
                        .numberOfSongsInAlbum((long) albumReview.getAlbum().getSongsInAlbum().size())
                        .build()).collect(Collectors.toList());
    }

    public AlbumNoticePostResponseDto getPostByPostNumber(Long id) {
        AlbumReview albumReview = albumReviewRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        albumReview.visit(); // 방문
        albumReviewRepository.save(albumReview);

        return AlbumNoticePostResponseDto.builder()
                .postNumber(id)
                .albumName(albumReview.getAlbum().getName())
                .author(albumReview.getAuthor())
                .hits(albumReview.getHits())
                .songsInAlbum(albumReview.getAlbum().getSongsInAlbum())
                .modifiedTime(albumReview.getUpdateTime())
                .artistName(albumReview.getAlbum().getArtistName())
                .dateOfIssue(albumReview.getAlbum().getDateOfIssue())
                .build();
    }

//    public String getContentByPostNumber(Long id) {
//        return albumReviewRepository.findById(id)
//                .orElseThrow(NoSuchElementException::new).getContent();
//    }

    public void update(Long id, String title, List<String> content, Double star) {
        AlbumReview albumReview = albumReviewRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        albumReview.modified(title, content, star); // update 시간 조정, 그리고 만일 앨범에 대한 정보를 수정하였어도, album 이 변경되어야지, 다른게 변경되는 것이 아니다.
        albumReviewRepository.save(albumReview);
    }

    public void delete(Long id) {
        AlbumReview albumReview = albumReviewRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);

        albumReviewRepository.delete(albumReview);
    }
}
