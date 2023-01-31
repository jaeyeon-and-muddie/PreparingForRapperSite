package com.skhu.practice.Sevice;


import com.skhu.practice.DTO.*;
import com.skhu.practice.Entity.mixtape.Mixtape;
import com.skhu.practice.Entity.mixtape.MixtapeIntro;
import com.skhu.practice.Repository.MixtapeIntroRepository;
import com.skhu.practice.Repository.MixtapeRepository;
import com.skhu.practice.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MixtapeService {
    private final MixtapeRepository mixtapeRepository;
    private final MixtapeIntroRepository mixtapeIntroRepository;
    private final UserRepository userRepository;


    public List<MixtapeIntroDto> mixtapeIntroList(){
        LocalDate now = LocalDate.now();
        List<MixtapeIntro> mixtapeIntroList = mixtapeIntroRepository.findAll();
        List<MixtapeIntroDto> mixtapeIntroDtos = new ArrayList<>();
        for(int i=0; i<mixtapeIntroList.size(); i++){
            Period period = Period.between(mixtapeIntroList.get(i).getCreatedDate(),now);
            if(period.getDays()>4){
                mixtapeIntroRepository.delete(mixtapeIntroList.get(i));
                continue;
            }
            MixtapeIntroDto mixtapeIntroDto = MixtapeIntroDto.builder()
                    .id(mixtapeIntroList.get(i).getId())
                    .title(mixtapeIntroList.get(i).getTitle())
                    .user(mixtapeIntroList.get(i).getUser())
                    .introduction(mixtapeIntroList.get(i).getIntroduction())
                    .ReleaseDate(mixtapeIntroList.get(i).getReleaseDate())
                    .preTag(mixtapeIntroList.get(i).getPreTag())
                    .numberOfSongs(mixtapeIntroList.get(i).getNumberOfSongs())
                    .recommendNumber(mixtapeIntroList.get(i).getRecommend().size())
                    .build();
            mixtapeIntroDtos.add(mixtapeIntroDto);
        }
        return mixtapeIntroDtos;
    }
    public List<MixtapeDto> mixtapeList(){
        List<MixtapeDto> mixtapeDtos = new ArrayList<>();
        List<Mixtape> mixtapes= mixtapeRepository.findAll();
        for(int i=0; i<mixtapes.size(); i++){
            MixtapeDto mixtapeDto = MixtapeDto.builder()
                    .id(mixtapes.get(i).getId())
                    .title(mixtapes.get(i).getTitle())
                    .user(mixtapes.get(i).getUser())
                    .build();
            mixtapeDtos.add(mixtapeDto);
        }
        return mixtapeDtos;
    }

    public void recommend(Long id, UserSessionDto user)
    {
        MixtapeIntro mixtape = mixtapeIntroRepository.findById(id).orElse(null);
        if (mixtape.getRecommend().size()<5) {
            System.out.println(mixtape.getRecommend().size());
            List<String> recommendList = mixtape.getRecommend();
            if (!recommendList.contains(user.getEmail())) {
                recommendList.add(user.getEmail());
                mixtape.setRecommend(recommendList);
                mixtapeIntroRepository.save(mixtape);
            }
        }
        if(mixtape.getRecommend().size()==5){
            Mixtape mixtapePass = Mixtape.builder()
                    .title(mixtape.getTitle())
                    .preTag(mixtape.getPreTag())
                    .introduction(mixtape.getIntroduction())
                    .numberOfSongs(mixtape.getNumberOfSongs())
                    .user(mixtape.getUser())
                    .build();
            mixtapeRepository.save(mixtapePass);
            mixtapeIntroRepository.delete(mixtape);
        }

    }
    public MixtapeDetailDto mixtapeDetail(Long id){
        Mixtape mixtapeDetailOG = mixtapeRepository.findById(id).orElse(null);
        MixtapeDetailDto mixtapeDetail = MixtapeDetailDto.builder()
                .id(mixtapeDetailOG.getId())
                .introduction(mixtapeDetailOG.getIntroduction())
                .title(mixtapeDetailOG.getTitle())
                .user(mixtapeDetailOG.getUser())
                .releaseDate(mixtapeDetailOG.getReleaseDate())
                .numberOfSongs(mixtapeDetailOG.getNumberOfSongs())
                .preTag(mixtapeDetailOG.getPreTag())
                .build();
        return mixtapeDetail;
    }

    public void plusMixtape(MixtapeCreateDto mixtapeCreate, UserSessionDto user) {
        MixtapeIntro mixtape = MixtapeIntro.builder()
                .user(userRepository.findByEmail(user.getEmail()).orElse(null))
                .title(mixtapeCreate.getTitle())
                .releaseDate(mixtapeCreate.getReleaseDate())
                .numberOfSongs(mixtapeCreate.getNumberOfSongs())
                .preTag(mixtapeCreate.getPreTag())
                .introduction(mixtapeCreate.getIntroduction())
                .build();
        mixtapeIntroRepository.save(mixtape);

    }

    public SoundCloudDto soundCloud(Long id) {

        MixtapeIntro mixtapeIntro = mixtapeIntroRepository.findById(id).orElse(null);

        SoundCloudDto soundCloud = SoundCloudDto.builder()
                .id(mixtapeIntro.getId())
                .recommendNumber(mixtapeIntro.getRecommend().size())
                .preTag(mixtapeIntro.getPreTag())
                .build();
        return soundCloud;

    }
}
