package com.sandbox.javaweatherapp.controllers;

import com.sandbox.javaweatherapp.models.WeatherResponse;
import com.sandbox.javaweatherapp.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;
    
    /**
     * Get weather data by zip code
     */
    @GetMapping("/zip/{zipCode}")
    public Mono<ResponseEntity<WeatherResponse>> getWeatherByZip(@PathVariable String zipCode) {
        return weatherService.getWeatherDataByZip(zipCode)
                .map(ResponseEntity::ok)
                .onErrorResume(throwable -> {
                    System.err.println("Error in getWeatherByZip: " + throwable.getMessage());
                    throwable.printStackTrace();
                    return Mono.just(ResponseEntity.badRequest().build());
                });
    }
    
    /**
     * Get weather data by coordinates
     */
    @GetMapping("/coordinates")
    public Mono<ResponseEntity<WeatherResponse>> getWeatherByCoordinates(
            @RequestParam double latitude, 
            @RequestParam double longitude) {
        return weatherService.getWeatherData(latitude, longitude)
                .map(ResponseEntity::ok)
                .onErrorResume(throwable -> {
                    System.err.println("Error in getWeatherByCoordinates: " + throwable.getMessage());
                    throwable.printStackTrace();
                    return Mono.just(ResponseEntity.badRequest().build());
                });
    }
    
    /**
     * Get coordinates from zip code
     */
    @GetMapping("/geocode/{zipCode}")
    public Mono<ResponseEntity<Map<String, Object>>> getCoordinates(@PathVariable String zipCode) {
        return weatherService.getCoordinatesFromZip(zipCode)
                .map(ResponseEntity::ok)
                .onErrorResume(throwable -> {
                    System.err.println("Error in getCoordinates: " + throwable.getMessage());
                    throwable.printStackTrace();
                    return Mono.just(ResponseEntity.badRequest().build());
                });
    }
}
