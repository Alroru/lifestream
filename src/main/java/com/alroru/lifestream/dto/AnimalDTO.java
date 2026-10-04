package com.alroru.lifestream.dto;

public record AnimalDTO(
        Long id,
        String commonName,
        String scientificName,
        String habitat,
        String diet,
        String conservationStatus,
        String description,
        String imageUrl) {
}