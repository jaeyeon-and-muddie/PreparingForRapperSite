package com.skhu.practice.Repository.Mixtape;

import com.skhu.practice.Entity.mixtape.Mixtape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MixtapeRepository extends JpaRepository<Mixtape, Long> {

}
