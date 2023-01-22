package com.skhu.practice.repository;

import com.skhu.practice.entity.Alarm;
import com.skhu.practice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findByUsersOrderByAlarmCreatedTimeDesc(Users users);
}
