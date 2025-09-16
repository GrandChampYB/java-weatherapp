package com.sandbox.javaweatherapp.services;

import com.sandbox.javaweatherapp.models.Favorite;
import com.sandbox.javaweatherapp.repositories.FavoriteRepository;
// import org.springframework.beans.factory.annotation.Autowired; // no longer needed
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    
    private final FavoriteRepository favoriteRepository;
    private final WeatherService weatherService;
    
    public FavoriteServiceImpl(FavoriteRepository favoriteRepository, WeatherService weatherService) {
        this.favoriteRepository = favoriteRepository;
        this.weatherService = weatherService;
    }
    
    /**
     * Get all favorites ordered by last accessed
     */
    @Override
    public List<Favorite> getAllFavorites() {
        return favoriteRepository.findAllOrderByLastAccessed();
    }
    
    /**
     * Get favorite by zip code
     */
    @Override
    public Optional<Favorite> getFavoriteByZipCode(String zipCode) {
        return favoriteRepository.findByZipCode(zipCode);
    }
    
    /**
     * Add a new favorite
     */
    @Override
    public Mono<Favorite> addFavorite(String zipCode) {
        // Check if already exists
        if (favoriteRepository.existsByZipCode(zipCode)) {
            return Mono.error(new IllegalArgumentException("Zip code already exists in favorites"));
        }
        
        // Get coordinates and location name
        return weatherService.getCoordinatesFromZip(zipCode)
                .map(coords -> {
                    Favorite favorite = new Favorite();
                    favorite.setZipCode(zipCode);
                    favorite.setLatitude((Double) coords.get("lat"));
                    favorite.setLongitude((Double) coords.get("lon"));
                    favorite.setLocationName((String) coords.get("locationName"));
                    favorite.setLastAccessed(LocalDateTime.now());
                    
                    return favoriteRepository.save(favorite);
                });
    }
    
    /**
     * Remove a favorite by zip code
     */
    @Override
    public boolean removeFavorite(String zipCode) {
        Optional<Favorite> favorite = favoriteRepository.findByZipCode(zipCode);
        if (favorite.isPresent()) {
            favoriteRepository.delete(favorite.get());
            return true;
        }
        return false;
    }
    
    /**
     * Update last accessed time for a favorite
     */
    @Override
    public void updateLastAccessed(String zipCode) {
        Optional<Favorite> favorite = favoriteRepository.findByZipCode(zipCode);
        if (favorite.isPresent()) {
            Favorite fav = favorite.get();
            fav.setLastAccessed(LocalDateTime.now());
            favoriteRepository.save(fav);
        }
    }
    
    /**
     * Search favorites by location name or zip code
     */
    @Override
    public List<Favorite> searchFavorites(String searchTerm) {
        return favoriteRepository.findByLocationNameOrZipCodeContainingIgnoreCase(searchTerm);
    }
    
    /**
     * Check if a zip code is already a favorite
     */
    @Override
    public boolean isFavorite(String zipCode) {
        return favoriteRepository.existsByZipCode(zipCode);
    }
}