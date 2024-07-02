package com.github.nicturtle.model.repository;

import com.github.nicturtle.model.entity.AromaOil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AromaOilRepository extends JpaRepository<AromaOil, Long> {
}

