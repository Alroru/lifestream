package com.alroru.lifestream.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "animal_entity")
public class AnimalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "common_name", nullable = false, length = 100)
    private String commonName;

    @Column(name = "scientific_name", nullable = false, length = 100)
    private String scientificName;

    @Column(name = "habitat", nullable = false, length = 100)
    private String habitat;

    @Column(name = "diet", nullable = false, length = 50)
    private String diet;

    @Column(name = "conservation_status", nullable = false, length = 50)
    private String conservationStatus;

    @Column(name = "description", nullable = false, length = 500)
    private String description;

    @Column(name = "image_url", length = 255)
    private String imageUrl;

    public AnimalEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public String getConservationStatus() {
        return conservationStatus;
    }

    public void setConservationStatus(String conservationStatus) {
        this.conservationStatus = conservationStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public AnimalEntity(String commonName, String scientificName, String habitat, String diet, String conservationStatus, String description) {
        this(commonName, scientificName, habitat, diet, conservationStatus, description, null);
    }

    public AnimalEntity(String commonName, String scientificName, String habitat, String diet, String conservationStatus, String description, String imageUrl) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.habitat = habitat;
        this.diet = diet;
        this.conservationStatus = conservationStatus;
        this.description = description;
        this.imageUrl = imageUrl;
    }




}
