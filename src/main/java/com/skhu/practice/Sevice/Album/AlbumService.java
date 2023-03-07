package com.skhu.practice.Sevice.Album;


import com.skhu.practice.DTO.AlbumDto.PlusAlbumDto;
import com.skhu.practice.DTO.AlbumDto.PlusSongDto;
import com.skhu.practice.Entity.album.Artist;
import com.skhu.practice.Entity.album.Song;
import com.skhu.practice.Repository.Album.AlbumRepository;
import com.skhu.practice.Repository.Album.ArtistRepository;
import com.skhu.practice.Repository.Album.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.skhu.practice.Entity.album.Album;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final SongRepository songRepository;

    public List<Album> albumList() {

        return albumRepository.findAll();
    }
    public Album albumInform(Long albumId){
        Album album = albumRepository.findById(albumId).orElse(null);
        return album;
    }
    public void plusAlbum(PlusAlbumDto plusAlbum){
        Artist artist =artistRepository.findByArtistName(plusAlbum.getArtistName()).orElse(null);
        Album album = Album.builder()
                .title(plusAlbum.getTitle())
                .releaseDate(plusAlbum.getReleaseDate())
                .introduction(plusAlbum.getIntroduction())
                .image(plusAlbum.getImage())
                .artist(artist)
                .numberOfSongs(plusAlbum.getNumberOfSongs())
                .build();
        albumRepository.save(album);
    }
    public void plusSong(PlusSongDto plusSongDto, String albumTitle){
        Album album = albumRepository.findByTitle(albumTitle);
        for(int i =0; i<plusSongDto.getPlusSongDtoList().size(); i++){
            Song song = Song.builder()
                    .title(plusSongDto.getPlusSongDtoList().get(i).getTitle())
                    .number(i+1)
                    .participants(plusSongDto.getPlusSongDtoList().get(i).getParticipants())
                    .album(album)
                    .build();
            songRepository.save(song);
        }

    }
    public List<Long> SongId(Long albumId){
        List<Long> songId = new ArrayList<Long>();
        List<Song> songs = songRepository.findAllByAlbumId(albumId);
        for(int i=0; i<songs.size(); i++){
            songId.add(songs.get(i).getId());
        }
        return songId;
    }

    public List<PlusSongDto> makeSongList(Integer NumberOfSongs) {
        List<PlusSongDto> plusSongDto = new ArrayList<PlusSongDto>(NumberOfSongs);
        for(int i=0; i<NumberOfSongs; i++){
            PlusSongDto plusSongDto1 = new PlusSongDto();
            plusSongDto.add(plusSongDto1);
        }
        return plusSongDto;
    }
}
