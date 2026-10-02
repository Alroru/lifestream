package com.alroru.lifestream.controller;

import com.alroru.lifestream.model.Animal;
import com.alroru.lifestream.repository.AnimalRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
public class AnimalController {

    AnimalRepository animalRepository;

    public AnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @RequestMapping("api/animals")
    public List<Animal> getAllAnimals(){
        return animalRepository.findAll();
    }

}
