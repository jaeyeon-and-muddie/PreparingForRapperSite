package com.skhu.practice.Sevice.Album;


import com.skhu.practice.DTO.AlbumDto.NotableHomeDto;
import com.skhu.practice.Entity.album.Album;
import com.skhu.practice.Repository.Album.AlbumRepository;
import com.skhu.practice.Repository.Album.AlbumReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotableService {
    private final AlbumReviewRepository albumReviewRepository;
    private final AlbumRepository albumRepository;

    public NotableHomeDto NotableHome(){
        List<Long> count = albumReviewRepository.getCountByAlbumReviewWithReview();
        List<Long> view = albumReviewRepository.getSumByAlbumReviewWithView();
        List<Long> star = albumReviewRepository.getAvgByAlbumReviewWithStar();
        List<Album> albumList1 = new ArrayList<>();
        List<Album> albumList2 = new ArrayList<>();
        List<Album> albumList3 = new ArrayList<>();
        for(int i =0; i<3; i++){
            albumList1.add(albumRepository.findById(count.get(i)).orElse(null));
        }
        for(int i =0; i<3; i++){
            albumList2.add(albumRepository.findById(view.get(i)).orElse(null));
        }
        for(int i =0; i<3; i++){
            albumList3.add(albumRepository.findById(star.get(i)).orElse(null));
        }

        NotableHomeDto Notable= NotableHomeDto.builder()
                .reviewNotes(albumList1)
                .viewNotes(albumList2)
                .starNotes(albumList3)
                .build();
        return Notable;
    }
}
