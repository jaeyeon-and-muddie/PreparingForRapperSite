package com.skhu.practice.repository;

import com.skhu.practice.entity.Visited;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitedRepository extends JpaRepository<Visited, Long> {

}
