package com.alroru.lifestream.controller;

import com.alroru.lifestream.model.dto.AnimalDTO;
import com.alroru.lifestream.repository.AnimalSummaryProjection;
import com.alroru.lifestream.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Animals", description = "Consulta del catálogo de animales")
public class AnimalController {

    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("/api/animals")
    @Operation(summary = "Buscar animales", description = "Lista paginada con filtros opcionales y combinables.")
    public Page<AnimalSummaryProjection> searchAnimals(@Parameter(description = "Contiene en nombre común o científico") @RequestParam(required = false) String name,
                                         @Parameter(description = "Igualdad exacta, insensible a mayúsculas") @RequestParam(required = false) String habitat,
                                         @Parameter(description = "Igualdad exacta, insensible a mayúsculas") @RequestParam(required = false) String diet,
                                         @Parameter(description = "Igualdad exacta, insensible a mayúsculas") @RequestParam(required = false) String conservationStatus,
                                         @PageableDefault(size = 20, sort = "id") @ParameterObject Pageable pageable) {
        return animalService.searchAnimals(name, habitat, diet, conservationStatus, pageable);
    }


    @GetMapping("/api/animals/count")
    @Operation(summary = "Contar animales")
    public long countAnimals() {
        return animalService.countAnimals();
    }

    @GetMapping("/api/animals/{id}")
    @Operation(summary = "Detalle de un animal")
    @ApiResponse(responseCode = "200", description = "Ficha encontrada")
    @ApiResponse(responseCode = "404", description = "No existe ese id", content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<AnimalDTO> getAnimalById(@Parameter(description = "Identificador del animal") @PathVariable Long id) {
        return ResponseEntity.ok(animalService.getAnimalById(id));
    }

}
