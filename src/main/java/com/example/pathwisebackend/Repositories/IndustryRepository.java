package com.example.pathwisebackend.Repositories;

import com.example.pathwisebackend.Models.Industry;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndustryRepository extends JpaRepository<Industry, Long> {
    List<Industry> findByOwnerId(Long userId);

    @Query("SELECT DISTINCT i FROM Industry i " +
            "LEFT JOIN FETCH i.jobRoles " +
            "WHERE i.owner.id = :userId")
    List<Industry> findAllByOwnerIdWithJobRoles(@Param("userId") Long userId);

    @EntityGraph(attributePaths = "jobRoles")
    List<Industry> findByOwner_Id(Long userId);
}
