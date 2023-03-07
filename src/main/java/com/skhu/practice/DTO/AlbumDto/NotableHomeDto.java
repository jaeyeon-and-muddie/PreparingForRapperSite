package com.skhu.practice.DTO.AlbumDto;

import com.skhu.practice.Entity.album.Album;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class NotableHomeDto {
    public List<Album> reviewNotes;

    public List<Album> viewNotes;

    public List<Album> starNotes;
}
