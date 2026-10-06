package com.alroru.lifestream.controller;

import com.alroru.lifestream.repository.AnimalSummaryProjection;
import com.alroru.lifestream.service.AnimalService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AnimalViewController {

    private final AnimalService animalService;

    public AnimalViewController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/")
    public String animals(@RequestParam(required = false) String name,
                          @RequestParam(required = false) String habitat,
                          @RequestParam(required = false) String diet,
                          @RequestParam(required = false) String conservationStatus,
                          @PageableDefault(size = 20, sort = "id") Pageable pageable,
                          Model model) {
        Page<AnimalSummaryProjection> page = animalService.searchAnimals(name, habitat, diet, conservationStatus, pageable);
        model.addAttribute("page", page);
        model.addAttribute("name", name);
        model.addAttribute("habitat", habitat);
        model.addAttribute("diet", diet);
        model.addAttribute("conservationStatus", conservationStatus);
        model.addAttribute("habitats", animalService.getHabitats());
        model.addAttribute("diets", animalService.getDiets());
        model.addAttribute("conservationStatuses", animalService.getConservationStatuses());
        return "animals";
    }
}
