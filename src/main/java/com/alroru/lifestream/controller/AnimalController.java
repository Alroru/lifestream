package com.alroru.lifestream.controller;

import com.alroru.lifestream.model.dto.AnimalDTO;
import com.alroru.lifestream.repository.AnimalSummaryProjection;
import com.alroru.lifestream.service.AnimalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/api/animals")
    public Page<AnimalSummaryProjection> searchAnimals(@RequestParam(required = false) String name,
                                         @RequestParam(required = false) String habitat,
                                         @RequestParam(required = false) String diet,
                                         @RequestParam(required = false) String conservationStatus,
                                         @PageableDefault(size = 20, sort = "id") Pageable pageable) {
        return animalService.searchAnimals(name, habitat, diet, conservationStatus, pageable);
    }


    @GetMapping("/api/animals/count")
    public long countAnimals() {
        return animalService.countAnimals();
    }

    @GetMapping("/api/animals/{id}")
    public ResponseEntity<AnimalDTO> getAnimalById(@PathVariable Long id) {
        return ResponseEntity.ok(animalService.getAnimalById(id));
    }

}
