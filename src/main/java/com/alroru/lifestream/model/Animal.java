package com.alroru.lifestream.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String commonName;
    private String scientificName;
    private String habitat;
    private String diet;
    private String conservationStatus;
    private String description;
    private String imageUrl;

    public Animal() {
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

    public Animal(String commonName, String scientificName, String habitat, String diet, String conservationStatus, String description) {
        this(commonName, scientificName, habitat, diet, conservationStatus, description, null);
    }

    public Animal(String commonName, String scientificName, String habitat, String diet, String conservationStatus, String description, String imageUrl) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.habitat = habitat;
        this.diet = diet;
        this.conservationStatus = conservationStatus;
        this.description = description;
        this.imageUrl = imageUrl;
    }




}
