package com.sandbox.javaweatherapp.repositories;

import com.sandbox.javaweatherapp.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    
    Optional<Favorite> findByZipCode(String zipCode);
    
    boolean existsByZipCode(String zipCode);
    
    @Query("SELECT f FROM Favorite f ORDER BY f.lastAccessed DESC NULLS LAST, f.createdAt DESC")
    List<Favorite> findAllOrderByLastAccessed();
    
    @Query("SELECT f FROM Favorite f WHERE f.locationName ILIKE %:searchTerm% OR f.zipCode LIKE %:searchTerm%")
    List<Favorite> findByLocationNameOrZipCodeContainingIgnoreCase(@Param("searchTerm") String searchTerm);
}
