package com.alroru.lifestream.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Ficha completa de un animal")
public record AnimalDTO(
        @Schema(description = "Identificador", example = "1") Long id,
        @Schema(example = "Lobo ibérico") String commonName,
        @Schema(example = "Canis lupus signatus") String scientificName,
        @Schema(example = "Bosques y montañas") String habitat,
        @Schema(example = "Carnívoro") String diet,
        @Schema(example = "Vulnerable") String conservationStatus,
        String description,
        @Schema(description = "Ruta local de la imagen", example = "/images/animals/lobo-iberico.jpg") String imageUrl) {
}