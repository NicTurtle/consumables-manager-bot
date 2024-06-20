package com.github.nicturtle.controller;

import com.github.nicturtle.model.entity.AromaOil;
import com.github.nicturtle.model.entity.Glass;
import com.github.nicturtle.model.entity.Wax;
import com.github.nicturtle.model.entity.Wick;
import com.github.nicturtle.service.AromaOilService;
import com.github.nicturtle.service.GlassService;
import com.github.nicturtle.service.WaxService;
import com.github.nicturtle.service.WickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ComponentScan
@RestController
@RequestMapping("/materials")
public class MaterialController {

    private final AromaOilService aromaOilService;
    private final GlassService glassService;
    private final WaxService waxService;
    private final WickService wickService;

    @Autowired
    public MaterialController(AromaOilService aromaOilService, GlassService glassService, WaxService waxService, WickService wickService) {
        this.aromaOilService = aromaOilService;
        this.glassService = glassService;
        this.waxService = waxService;
        this.wickService = wickService;
    }

    // Endpoints for AromaOil
    @GetMapping("/aroma-oil")
    public List<AromaOil> getAllAromaOils() {
        return aromaOilService.getAllAromaOils();
    }

    @GetMapping("/aroma-oil/{id}")
    public AromaOil getAromaOilById(@PathVariable Long id) {
        return aromaOilService.getAromaOilById(id);
    }

    @PostMapping("/aroma-oil")
    public AromaOil createAromaOil(@RequestBody AromaOil aromaOil) {
        return aromaOilService.saveAromaOil(aromaOil);
    }

    @DeleteMapping("/aroma-oil/{id}")
    public void deleteAromaOil(@PathVariable Long id) {
        aromaOilService.deleteAromaOil(id);
    }

    // Endpoints for Glass
    @GetMapping("/glass")
    public List<Glass> getAllGlass() {
        return glassService.getAllGlass();
    }

    @GetMapping("/glass/{id}")
    public Glass getGlassById(@PathVariable Long id) {
        return glassService.getGlassById(id);
    }

    @PostMapping("/glass")
    public Glass createGlass(@RequestBody Glass glass) {
        return glassService.saveGlass(glass);
    }

    @DeleteMapping("/glass/{id}")
    public void deleteGlass(@PathVariable Long id) {
        glassService.deleteGlass(id);
    }

    // Endpoints for Wax
    @GetMapping("/wax")
    public List<Wax> getAllWax() {
        return waxService.getAllWax();
    }

    @GetMapping("/wax/{id}")
    public Wax getWaxById(@PathVariable Long id) {
        return waxService.getWaxById(id);
    }

    @PostMapping("/wax")
    public Wax createWax(@RequestBody Wax wax) {
        return waxService.saveWax(wax);
    }

    @DeleteMapping("/wax/{id}")
    public void deleteWax(@PathVariable Long id) {
        waxService.deleteWax(id);
    }

    // Endpoints for Wick
    @GetMapping("/wick")
    public List<Wick> getAllWicks() {
        return wickService.getAllWicks();
    }

    @GetMapping("/wick/{id}")
    public Wick getWickById(@PathVariable Long id) {
        return wickService.getWickById(id);
    }

    @PostMapping("/wick")
    public Wick createWick(@RequestBody Wick wick) {
        return wickService.saveWick(wick);
    }

    @DeleteMapping("/wick/{id}")
    public void deleteWick(@PathVariable Long id) {
        wickService.deleteWick(id);
    }
}


