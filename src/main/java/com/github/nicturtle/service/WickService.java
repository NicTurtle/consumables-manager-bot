package com.github.nicturtle.service;

import com.github.nicturtle.model.entity.Wick;
import com.github.nicturtle.model.repository.GlassRepository;
import com.github.nicturtle.model.repository.WickRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WickService {
    @Autowired
    private WickRepository wickRepository;

    public List<Wick> getAllWicks() {
        return wickRepository.findAll();
    }

    public Wick getWickById(Long id) {
        return wickRepository.findById(id).orElse(null);
    }

    public Wick saveWick(Wick wick) {
        return wickRepository.save(wick);
    }

    public void deleteWick(Long id) {
        wickRepository.deleteById(id);
    }
}

