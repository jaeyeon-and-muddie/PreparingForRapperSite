package com.skhu.practice.DTO;

import com.skhu.practice.Entity.Album;
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
