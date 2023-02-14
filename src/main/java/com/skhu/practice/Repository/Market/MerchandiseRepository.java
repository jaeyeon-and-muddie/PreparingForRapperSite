package com.skhu.practice.Repository.Market;

import com.skhu.practice.Entity.market.Merchandise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {
    List<Merchandise> findAllByUserId(long id);
}
