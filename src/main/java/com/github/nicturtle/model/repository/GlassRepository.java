package com.github.nicturtle.model.repository;

import com.github.nicturtle.model.entity.Glass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GlassRepository extends JpaRepository<Glass, Long> {
}

