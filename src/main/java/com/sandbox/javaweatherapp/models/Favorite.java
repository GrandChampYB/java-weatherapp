package com.sandbox.javaweatherapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorites")
public class Favorite {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Zip code is required")
    @Pattern(regexp = "^\\d{5}$", message = "Zip code must be 5 digits")
    @Column(name = "zip_code", nullable = false, unique = true)
    private String zipCode;
    
    @Column(name = "location_name")
    private String locationName;
    
    @Column(name = "latitude")
    private Double latitude;
    
    @Column(name = "longitude")
    private Double longitude;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "last_accessed")
    private LocalDateTime lastAccessed;
    
    // Constructors
    public Favorite() {
        this.createdAt = LocalDateTime.now();
    }
    
    public Favorite(String zipCode, String locationName, Double latitude, Double longitude) {
        this();
        this.zipCode = zipCode;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public String getLocationName() {
        return locationName;
    }
    
    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }
    
    public void setLastAccessed(LocalDateTime lastAccessed) {
        this.lastAccessed = lastAccessed;
    }
    
    @PreUpdate
    public void preUpdate() {
        this.lastAccessed = LocalDateTime.now();
    }
}
