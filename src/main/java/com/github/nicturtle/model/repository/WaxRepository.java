package com.github.nicturtle.model.repository;

import com.github.nicturtle.model.entity.Wax;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaxRepository extends JpaRepository<Wax, Long> {
}
