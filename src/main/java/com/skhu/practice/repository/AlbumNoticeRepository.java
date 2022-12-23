package com.skhu.practice.repository;

import com.skhu.practice.entity.AlbumNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumNoticeRepository extends JpaRepository<AlbumNotice, Long> {
}
