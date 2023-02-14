package com.skhu.practice.Controller;


import com.skhu.practice.DTO.AlbumDto.PlusAlbumDto;
import com.skhu.practice.DTO.AlbumDto.PlusSongDto;
import com.skhu.practice.DTO.NavbarDto;
import com.skhu.practice.DTO.UserSessionDto;
import com.skhu.practice.Sevice.Album.AlbumService;
import com.skhu.practice.Sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes("user")
public class AlbumController {

    private final AlbumService albumService;
    private final UserService userService;
    @GetMapping("plusAlbum")
    public ModelAndView albumPlus(PlusAlbumDto plusAlbumDto){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("plusAlbum");
        mv.addObject("plusAlbum", plusAlbumDto);
        return mv;

    }

    @PostMapping("plusAlbum")
    public ModelAndView albumInsert(@ModelAttribute("plusAlbum") PlusAlbumDto plusAlbumDto) throws UnsupportedEncodingException {
        albumService.plusAlbum(plusAlbumDto);
        String encodedParam = URLEncoder.encode(plusAlbumDto.getTitle(), "UTF-8");
        ModelAndView mv = new ModelAndView("redirect:plusSong?numberOfSongs="+plusAlbumDto.getNumberOfSongs()
        +"&albumTitle="+encodedParam);
        System.out.println(plusAlbumDto.getTitle());

        return mv;
    }
    @GetMapping("plusSong")
    public ModelAndView songPlus(@RequestParam("numberOfSongs") Integer NumberOfSongs,
                                 @RequestParam("albumTitle") String albumTitle
                                 ){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("plusSong");
        mv.addObject("plusSongs", albumService.makeSongList(NumberOfSongs));
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
