package com.alroru.lifestream.repository;

import com.alroru.lifestream.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
