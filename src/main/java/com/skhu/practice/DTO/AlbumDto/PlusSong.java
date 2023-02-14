package com.skhu.practice.DTO.AlbumDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlusSong {
    public String title;

    public String participants;


    @Override
    public String toString(){
        return this.title+" "+this.participants;
    }
}
