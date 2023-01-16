package com.skhu.practice.Sevice;


import com.skhu.practice.DTO.PlusAlbumDto;
import com.skhu.practice.DTO.PlusSongDto;
import com.skhu.practice.Entity.Artist;
import com.skhu.practice.Entity.Song;
import com.skhu.practice.Repository.AlbumRepository;
import com.skhu.practice.Repository.ArtistRepository;
import com.skhu.practice.Repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.skhu.practice.Entity.Album;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Album album = new Album();
        album.setTitle(plusAlbum.getTitle());
        album.setIntroduction(plusAlbum.getIntroduction());
        album.setReleaseDate(plusAlbum.getReleaseDate());
        album.setNumberOfSongs(plusAlbum.getNumberOfSongs());
        Artist artist =artistRepository.findByArtistName(plusAlbum.getArtistName());
        album.setArtist(artist);

        albumRepository.save(album);
    }
    public void plusSong(PlusSongDto plusSongDto, String albumTitle){
        Album album = albumRepository.findByTitle(albumTitle);
        for(int i =0; i<plusSongDto.getPlusSongDtoList().size(); i++){
            Song song = new Song();
            song.setTitle(plusSongDto.getPlusSongDtoList().get(i).getTitle());
            song.setNumber(i+1);
            song.setParticipants(plusSongDto.getPlusSongDtoList().get(i).getParticipants());
            song.setAlbum(album);
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
}
