package com.github.nicturtle.service;

import com.github.nicturtle.model.entity.Glass;
import com.github.nicturtle.model.repository.GlassRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlassService {
    @Autowired
    private GlassRepository glassRepository;

    public List<Glass> getAllGlass() {
        return glassRepository.findAll();
    }

    public Glass getGlassById(Long id) {
        return glassRepository.findById(id).orElse(null);
    }

    public Glass saveGlass(Glass glass) {
        return glassRepository.save(glass);
    }

    public void deleteGlass(Long id) {
        glassRepository.deleteById(id);
    }
}
