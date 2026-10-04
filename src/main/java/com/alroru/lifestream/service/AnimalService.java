package com.alroru.lifestream.service;

import com.alroru.lifestream.dto.AnimalDTO;
import com.alroru.lifestream.dto.AnimalSummaryDTO;
import com.alroru.lifestream.exception.AnimalNotFoundException;
import com.alroru.lifestream.model.Animal;
import com.alroru.lifestream.repository.AnimalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public Page<AnimalSummaryDTO> searchAnimals(String name, String habitat, String diet, String conservationStatus, Pageable pageable) {
        return animalRepository.search(normalize(name), normalize(habitat), normalize(diet), normalize(conservationStatus), pageable);
    }

    public AnimalDTO getAnimalById(Long id) {
        return animalRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new AnimalNotFoundException(id));
    }

    public long countAnimals() {
        return animalRepository.count();
    }

    private AnimalDTO toDTO(Animal animal) {
        return new AnimalDTO(
                animal.getId(),
                animal.getCommonName(),
                animal.getScientificName(),
                animal.getHabitat(),
                animal.getDiet(),
                animal.getConservationStatus(),
                animal.getDescription(),
                animal.getImageUrl());
    }

    private String normalize(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }
}
