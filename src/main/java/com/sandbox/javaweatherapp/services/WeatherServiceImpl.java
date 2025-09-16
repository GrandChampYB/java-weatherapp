package com.sandbox.javaweatherapp.services;

import com.sandbox.javaweatherapp.models.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class WeatherServiceImpl implements WeatherService {
    
    private final WebClient webClient;
    
    @Value("${weather.api.base-url}")
    private String weatherApiBaseUrl;
    
    @Value("${weather.api.geocoding-url}")
    private String geocodingApiUrl;
    
    public WeatherServiceImpl() {
        this.webClient = WebClient.builder().build();
    }
    
    /**
     * Get coordinates from zip code using OpenStreetMap Nominatim service
     */
    @Override
    public Mono<Map<String, Object>> getCoordinatesFromZip(String zipCode) {
        String url = geocodingApiUrl + 
                "?format=json&q=" + zipCode + 
                "&countrycodes=us&limit=1";
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Map.class)
                .next()
                .map(this::extractCoordinates);
    }
    
    /**
     * Get weather data from Open-Meteo API
     */
    @Override
    public Mono<WeatherResponse> getWeatherData(double latitude, double longitude) {
        String url = weatherApiBaseUrl + 
                "?latitude=" + latitude + 
                "&longitude=" + longitude +
                "&hourly=temperature_2m,relative_humidity_2m,wind_speed_10m,precipitation,weather_code" +
                "&daily=temperature_2m_max,temperature_2m_min,precipitation_sum,weather_code" +
                "&timezone=auto";
        
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(WeatherResponse.class);
    }
    
    /**
     * Get weather data by zip code (combines geocoding and weather API calls)
     */
    @Override
    public Mono<WeatherResponse> getWeatherDataByZip(String zipCode) {
        return getCoordinatesFromZip(zipCode)
                .flatMap(coords -> {
                    double lat = (Double) coords.get("lat");
                    double lon = (Double) coords.get("lon");
                    return getWeatherData(lat, lon);
                });
    }
    
    /**
     * Extract coordinates and location name from geocoding response
     */
    private Map<String, Object> extractCoordinates(Map<String, Object> response) {
        Map<String, Object> result = new HashMap<>();
        
        if (response.containsKey("lat") && response.containsKey("lon")) {
            result.put("lat", Double.parseDouble(response.get("lat").toString()));
            result.put("lon", Double.parseDouble(response.get("lon").toString()));
        }
        
        if (response.containsKey("display_name")) {
            String displayName = response.get("display_name").toString();
            // Extract city and state from display name
            String[] parts = displayName.split(",");
            if (parts.length >= 2) {
                result.put("locationName", parts[0].trim() + ", " + parts[1].trim());
            } else {
                result.put("locationName", displayName);
            }
        }
        
        return result;
    }
}