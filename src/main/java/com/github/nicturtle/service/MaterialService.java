package com.github.nicturtle.service;

import com.github.nicturtle.model.entity.Material;
import com.github.nicturtle.model.repository.GlassRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class MaterialService<T extends Material> {

    // Declares a generic type parameter 'T' that extends 'Material'
    protected final JpaRepository<T, Long> repository;

    // Constructor that injects the 'repository' dependency
    public MaterialService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    // Abstract methods that subclasses must implement
    public abstract List<T> getAll();

    public abstract T getById(Long id);

    public abstract T save(T material);

    public abstract void delete(Long id);
}


