package com.alroru.lifestream.repository;

import com.alroru.lifestream.model.entity.AnimalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Long> {

    @Query(value = """
            SELECT a.id AS id, a.commonName AS commonName FROM AnimalEntity a
            WHERE (:name IS NULL
                   OR LOWER(a.commonName) LIKE LOWER(CONCAT('%', :name, '%'))
                   OR LOWER(a.scientificName) LIKE LOWER(CONCAT('%', :name, '%')))
              AND (:habitat IS NULL OR LOWER(a.habitat) = LOWER(:habitat))
              AND (:diet IS NULL OR LOWER(a.diet) = LOWER(:diet))
              AND (:status IS NULL OR LOWER(a.conservationStatus) = LOWER(:status))
            """,
            countQuery = """
            SELECT COUNT(a) FROM AnimalEntity a
            WHERE (:name IS NULL
                   OR LOWER(a.commonName) LIKE LOWER(CONCAT('%', :name, '%'))
                   OR LOWER(a.scientificName) LIKE LOWER(CONCAT('%', :name, '%')))
              AND (:habitat IS NULL OR LOWER(a.habitat) = LOWER(:habitat))
              AND (:diet IS NULL OR LOWER(a.diet) = LOWER(:diet))
              AND (:status IS NULL OR LOWER(a.conservationStatus) = LOWER(:status))
            """)
    Page<AnimalSummaryProjection> search(@Param("name") String name,
                                  @Param("habitat") String habitat,
                                  @Param("diet") String diet,
                                  @Param("status") String status,
                                  Pageable pageable);

    @Query("SELECT DISTINCT a.habitat FROM AnimalEntity a ORDER BY a.habitat")
    List<String> findDistinctHabitats();

    @Query("SELECT DISTINCT a.diet FROM AnimalEntity a ORDER BY a.diet")
    List<String> findDistinctDiets();

    @Query("SELECT DISTINCT a.conservationStatus FROM AnimalEntity a ORDER BY a.conservationStatus")
    List<String> findDistinctConservationStatuses();
}
