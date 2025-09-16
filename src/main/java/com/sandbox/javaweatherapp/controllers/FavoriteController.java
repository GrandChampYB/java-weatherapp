package com.sandbox.javaweatherapp.controllers;

import com.sandbox.javaweatherapp.models.Favorite;
import com.sandbox.javaweatherapp.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin(origins = "*")
public class FavoriteController {
    
    @Autowired
    private FavoriteService favoriteService;
    
    /**
     * Get all favorites
     */
    @GetMapping
    public ResponseEntity<List<Favorite>> getAllFavorites() {
        List<Favorite> favorites = favoriteService.getAllFavorites();
        return ResponseEntity.ok(favorites);
    }
    
    /**
     * Add a new favorite
     */
    @PostMapping("/{zipCode}")
    public Mono<ResponseEntity<Favorite>> addFavorite(@PathVariable String zipCode) {
        return favoriteService.addFavorite(zipCode)
                .map(ResponseEntity::ok)
                .onErrorReturn(ResponseEntity.badRequest().build());
    }
    
    /**
     * Remove a favorite
     */
    @DeleteMapping("/{zipCode}")
    public ResponseEntity<String> removeFavorite(@PathVariable String zipCode) {
        boolean removed = favoriteService.removeFavorite(zipCode);
        if (removed) {
            return ResponseEntity.ok("Favorite removed successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    /**
     * Check if a zip code is a favorite
     */
    @GetMapping("/{zipCode}/exists")
    public ResponseEntity<Boolean> isFavorite(@PathVariable String zipCode) {
        boolean isFavorite = favoriteService.isFavorite(zipCode);
        return ResponseEntity.ok(isFavorite);
    }
    
    /**
     * Search favorites
     */
    @GetMapping("/search")
    public ResponseEntity<List<Favorite>> searchFavorites(@RequestParam String q) {
        List<Favorite> favorites = favoriteService.searchFavorites(q);
        return ResponseEntity.ok(favorites);
    }
    
    /**
     * Update last accessed time
     */
    @PutMapping("/{zipCode}/access")
    public ResponseEntity<String> updateLastAccessed(@PathVariable String zipCode) {
        favoriteService.updateLastAccessed(zipCode);
        return ResponseEntity.ok("Last accessed time updated");
    }
}
