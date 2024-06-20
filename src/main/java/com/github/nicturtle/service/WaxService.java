package com.github.nicturtle.service;

import com.github.nicturtle.model.entity.Wax;
import com.github.nicturtle.model.repository.WaxRepository;
import com.github.nicturtle.model.repository.WickRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WaxService {
    @Autowired
    private WaxRepository waxRepository;

    public List<Wax> getAllWax() {
        return waxRepository.findAll();
    }

    public Wax getWaxById(Long id) {
        return waxRepository.findById(id).orElse(null);
    }

    public Wax saveWax(Wax wax) {
        return waxRepository.save(wax);
    }

    public void deleteWax(Long id) {
        waxRepository.deleteById(id);
    }
}

