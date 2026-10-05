package com.alroru.lifestream.service;

import com.alroru.lifestream.model.dto.AnimalDTO;
import com.alroru.lifestream.repository.AnimalSummaryProjection;
import com.alroru.lifestream.model.entity.AnimalEntity;
import com.alroru.lifestream.model.exception.AnimalNotFoundException;
import com.alroru.lifestream.repository.AnimalRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalServiceImpl(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @Override
    public Page<AnimalSummaryProjection> searchAnimals(String name, String habitat, String diet, String conservationStatus, Pageable pageable) {
        return animalRepository.search(normalize(name), normalize(habitat), normalize(diet), normalize(conservationStatus), pageable);
    }

    @Override
    public AnimalDTO getAnimalById(Long id) {
        return animalRepository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new AnimalNotFoundException(id));
    }

    @Override
    public long countAnimals() {
        return animalRepository.count();
    }

    private AnimalDTO toDTO(AnimalEntity animalEntity) {
        return new AnimalDTO(
                animalEntity.getId(),
                animalEntity.getCommonName(),
                animalEntity.getScientificName(),
                animalEntity.getHabitat(),
                animalEntity.getDiet(),
                animalEntity.getConservationStatus(),
                animalEntity.getDescription(),
                animalEntity.getImageUrl());
    }

    private String normalize(String value) {
        return value == null || value.isBlank() ? null : value.trim();
    }
}
