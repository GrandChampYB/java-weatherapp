package com.sandbox.javaweatherapp.services;

import com.sandbox.javaweatherapp.models.WeatherResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

public interface WeatherService {
    
    /**
     * Get coordinates from zip code using OpenStreetMap Nominatim service
     */
    Mono<Map<String, Object>> getCoordinatesFromZip(String zipCode);
    
    /**
     * Get weather data from Open-Meteo API
     */
    Mono<WeatherResponse> getWeatherData(double latitude, double longitude);
    
    /**
     * Get weather data by zip code (combines geocoding and weather API calls)
     */
    Mono<WeatherResponse> getWeatherDataByZip(String zipCode);
}
