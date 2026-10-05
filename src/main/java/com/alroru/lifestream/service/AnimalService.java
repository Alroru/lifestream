package com.alroru.lifestream.service;

import com.alroru.lifestream.model.dto.AnimalDTO;
import com.alroru.lifestream.repository.AnimalSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AnimalService {

    Page<AnimalSummaryProjection> searchAnimals(String name, String habitat, String diet, String conservationStatus, Pageable pageable);

    AnimalDTO getAnimalById(Long id);

    long countAnimals();
}
