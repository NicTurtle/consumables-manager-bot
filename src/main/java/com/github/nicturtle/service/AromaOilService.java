package com.github.nicturtle.service;

import com.github.nicturtle.model.entity.AromaOil;
import com.github.nicturtle.model.repository.AromaOilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AromaOilService {

    private final AromaOilRepository aromaOilRepository;

    @Autowired
    public AromaOilService(AromaOilRepository aromaOilRepository) {
        this.aromaOilRepository = aromaOilRepository;
    }

    public List<AromaOil> getAllAromaOils() {
        return aromaOilRepository.findAll();
    }

    public AromaOil getAromaOilById(Long id) {
        return aromaOilRepository.findById(id).orElse(null);
    }

    public AromaOil saveAromaOil(AromaOil aromaOil) {
        return aromaOilRepository.save(aromaOil);
    }

    public void deleteAromaOil(Long id) {
        aromaOilRepository.deleteById(id);
    }
}
