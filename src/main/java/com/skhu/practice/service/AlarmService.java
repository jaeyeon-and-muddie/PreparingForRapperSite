package com.skhu.practice.service;

import com.skhu.practice.dto.AlarmResponseDto;
import com.skhu.practice.dto.TagDto;
import com.skhu.practice.entity.Alarm;
import com.skhu.practice.repository.AlarmRepository;
import com.skhu.practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final UserRepository userRepository;
    private final UrlToTitleService urlToTitleService;

    public List<AlarmResponseDto> findAllAlarm(Principal principal) {
        return alarmRepository.findByUsersOrderByAlarmCreatedTimeDesc(
                userRepository
                        .findByUsername(principal.getName())
                        .orElseThrow(NoSuchElementException::new))
                .stream()
                .map(Alarm::toResponseDto)
                .collect(Collectors.toList());
    }

    public String redirectByAlarm(Long albumId) {
        Alarm alarm = alarmRepository.findById(albumId).orElseThrow(NoSuchElementException::new);
        alarm.userInquiry();
        alarmRepository.save(alarm); // 이전에 변경이 되어있었는지를 확인하여, 실제로 저장하지 않을 수 있을 것 같은데 지금은 여기까지의 최적화는 적용하지 말자.
        return alarm.getUrl();
    }

    public void saveAlarmWhenTagContainMessage(String message, String url, String fromUsername) {
        String title = urlToTitleService.getTitleByUrl("http://localhost:8088/" + url);
        char tagChar = '@';

        for (int index = 0; index < message.length(); index++) {
            if (message.charAt(index) == tagChar) {
                String toUsername = extractToUsername(message, index + 1);
                saveAlarmWhenExistByUsername(toUsername, url, title, fromUsername);
                index += toUsername.length() + 1;
            }
        }
    }

    private String extractToUsername(String message, int start) {
        StringBuilder toUsername = new StringBuilder();
        char SPACE = ' ';

        for (int index = start; index < message.length(); index++) {
            char charThisIndex = message.charAt(index);
            if (charThisIndex == SPACE) {
                break;
            }

            toUsername.append(charThisIndex);
        }

        return toUsername.toString();
    }

    private void saveAlarmWhenExistByUsername(String username, String url,
                                              String title, String fromUsername) {
        if (userRepository.existsByUsername(username) && !username.equals(fromUsername)) {
            alarmRepository.save(TagDto.builder()
                    .toUser(userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new))
                    .url(url)
                    .title(title)
                    .fromUsername(fromUsername)
                    .build()
                    .toAlarm());
        }
    }
}
