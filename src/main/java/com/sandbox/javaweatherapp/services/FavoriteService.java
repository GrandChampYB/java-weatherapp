package com.sandbox.javaweatherapp.services;

import com.sandbox.javaweatherapp.models.Favorite;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface FavoriteService {
    
    /**
     * Get all favorites ordered by last accessed
     */
    List<Favorite> getAllFavorites();
    
    /**
     * Get favorite by zip code
     */
    Optional<Favorite> getFavoriteByZipCode(String zipCode);
    
    /**
     * Add a new favorite
     */
    Mono<Favorite> addFavorite(String zipCode);
    
    /**
     * Remove a favorite by zip code
     */
    boolean removeFavorite(String zipCode);
    
    /**
     * Update last accessed time for a favorite
     */
    void updateLastAccessed(String zipCode);
    
    /**
     * Search favorites by location name or zip code
     */
    List<Favorite> searchFavorites(String searchTerm);
    
    /**
     * Check if a zip code is already a favorite
     */
    boolean isFavorite(String zipCode);
}
