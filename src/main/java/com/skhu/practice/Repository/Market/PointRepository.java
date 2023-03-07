package com.skhu.practice.Repository.Market;

import com.skhu.practice.Entity.market.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
    Point findByUserId(Long id);
}
