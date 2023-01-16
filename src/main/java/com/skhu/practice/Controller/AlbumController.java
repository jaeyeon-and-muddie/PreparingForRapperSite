package com.skhu.practice.Controller;


import com.skhu.practice.DTO.PlusAlbumDto;
import com.skhu.practice.DTO.PlusSongDto;
import com.skhu.practice.Sevice.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    @GetMapping("plusAlbum")
    public ModelAndView albumPlus(PlusAlbumDto plusAlbumDto){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("plusAlbum");
        mv.addObject("plusAlbum", plusAlbumDto);
        return mv;

    }

    @PostMapping("plusAlbum")
    public ModelAndView albumInsert(@ModelAttribute("plusAlbum") PlusAlbumDto plusAlbumDto){
        albumService.plusAlbum(plusAlbumDto);
        ModelAndView mv = new ModelAndView("redirect:plusSong?numberOfSongs="+plusAlbumDto.getNumberOfSongs()
        +"&albumTitle="+plusAlbumDto.getTitle());
        System.out.println(plusAlbumDto.getTitle());

        return mv;
    }
    @GetMapping("plusSong")
    public ModelAndView songPlus(@RequestParam("numberOfSongs") Integer NumberOfSongs,
                                 @RequestParam("albumTitle") String albumTitle
                                 ){
        ModelAndView mv = new ModelAndView();
        List<PlusSongDto> plusSongDto = new ArrayList<PlusSongDto>(NumberOfSongs);
        for(int i=0; i<NumberOfSongs; i++){
            PlusSongDto plusSongDto1 = new PlusSongDto();
            plusSongDto.add(plusSongDto1);
        }
        System.out.println(plusSongDto.size());
        mv.setViewName("plusSong");
        mv.addObject("plusSongs", plusSongDto);
        mv.addObject("albumTitle", albumTitle);
        return mv;
    }
    @PostMapping("plusSong")
    public ModelAndView songInsert(PlusSongDto plusSongDto, String albumTitle){
        ModelAndView mv = new ModelAndView("redirect:home");
        System.out.println(plusSongDto.getPlusSongDtoList());
        System.out.println(albumTitle);
        albumService.plusSong(plusSongDto, albumTitle);
        return mv;
    }
}
