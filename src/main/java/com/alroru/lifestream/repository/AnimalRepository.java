package com.alroru.lifestream.repository;

import com.alroru.lifestream.dto.AnimalSummaryDTO;
import com.alroru.lifestream.model.Animal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query(value = """
            SELECT new com.alroru.lifestream.dto.AnimalSummaryDTO(a.id, a.commonName) FROM Animal a
            WHERE (:name IS NULL
                   OR LOWER(a.commonName) LIKE LOWER(CONCAT('%', :name, '%'))
                   OR LOWER(a.scientificName) LIKE LOWER(CONCAT('%', :name, '%')))
              AND (:habitat IS NULL OR LOWER(a.habitat) = LOWER(:habitat))
              AND (:diet IS NULL OR LOWER(a.diet) = LOWER(:diet))
              AND (:status IS NULL OR LOWER(a.conservationStatus) = LOWER(:status))
            """,
            countQuery = """
            SELECT COUNT(a) FROM Animal a
            WHERE (:name IS NULL
                   OR LOWER(a.commonName) LIKE LOWER(CONCAT('%', :name, '%'))
                   OR LOWER(a.scientificName) LIKE LOWER(CONCAT('%', :name, '%')))
              AND (:habitat IS NULL OR LOWER(a.habitat) = LOWER(:habitat))
              AND (:diet IS NULL OR LOWER(a.diet) = LOWER(:diet))
              AND (:status IS NULL OR LOWER(a.conservationStatus) = LOWER(:status))
            """)
    Page<AnimalSummaryDTO> search(@Param("name") String name,
                                  @Param("habitat") String habitat,
                                  @Param("diet") String diet,
                                  @Param("status") String status,
                                  Pageable pageable);
}
