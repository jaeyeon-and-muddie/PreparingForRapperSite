package com.skhu.practice.entity;

import com.skhu.practice.dto.AlarmResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@ToString
@Table(name = "alarm")
public class Alarm { // 여기서 생성한 시각은 BaseEntity CreatedDate 로 진행하자.

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Users.class)
    @JoinColumn(name = "users")
    private Users users;

    @Column(name = "url")
    private String url;

    @Column(name = "message", columnDefinition = "LONGTEXT")
    private String message;

    @Column(name = "is_user_checked")
    private Boolean isUserChecked;

    @Column(name = "alarm_created_time")
    private LocalDateTime alarmCreatedTime;

    @PrePersist
    private void prePersist() { // 확인하게 되면,
        if (isUserChecked == null) {
            isUserChecked = false;
        }

        if (alarmCreatedTime == null) {
            alarmCreatedTime = LocalDateTime.now();
        }
    }

    public void userInquiry() {
        isUserChecked = true;
    }

    public AlarmResponseDto toResponseDto() {
        return AlarmResponseDto.builder()
                .id(this.id)
                .message(this.message)
                .isUserChecked(this.isUserChecked)
                .alarmCreatedTime(this.alarmCreatedTime)
                .build();
    }
}
