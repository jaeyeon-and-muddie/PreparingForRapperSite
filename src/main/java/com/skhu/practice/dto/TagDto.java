package com.skhu.practice.dto;

import com.skhu.practice.entity.Alarm;
import com.skhu.practice.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class TagDto {

    private Users toUser;

    private String url;

    private String title;

    private String fromUsername;

    public Alarm toAlarm() {
        return Alarm.builder()
                .users(this.toUser)
                .url(this.url)
                .message(makeMessage())
                .build();
    }

    private String makeMessage() {
        return fromUsername + "님이 " + title + "에서 " + toUser.getUsername() + "님을 태그하셨습니다.";
    }
}
