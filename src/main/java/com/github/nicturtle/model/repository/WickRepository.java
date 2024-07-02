package com.github.nicturtle.model.repository;

import com.github.nicturtle.model.entity.Wick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WickRepository extends JpaRepository<Wick, Long> {
}
