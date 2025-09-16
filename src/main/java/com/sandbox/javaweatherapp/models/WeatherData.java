package com.sandbox.javaweatherapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public class WeatherData {
    
    @JsonProperty("time")
    private String time;
    
    @JsonProperty("temperature_2m")
    private Double temperature;
    
    @JsonProperty("weather_code")
    private Integer weatherCode;
    
    @JsonProperty("relative_humidity_2m")
    private Integer humidity;
    
    @JsonProperty("wind_speed_10m")
    private Double windSpeed;
    
    @JsonProperty("precipitation")
    private Double precipitation;
    
    // Constructors
    public WeatherData() {}
    
    public WeatherData(String time, Double temperature, Integer weatherCode, 
                      Integer humidity, Double windSpeed, Double precipitation) {
        this.time = time;
        this.temperature = temperature;
        this.weatherCode = weatherCode;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.precipitation = precipitation;
    }
    
    // Getters and Setters
    public String getTime() {
        return time;
    }
    
    public void setTime(String time) {
        this.time = time;
    }
    
    public Double getTemperature() {
        return temperature;
    }
    
    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }
    
    public Integer getWeatherCode() {
        return weatherCode;
    }
    
    public void setWeatherCode(Integer weatherCode) {
        this.weatherCode = weatherCode;
    }
    
    public Integer getHumidity() {
        return humidity;
    }
    
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }
    
    public Double getWindSpeed() {
        return windSpeed;
    }
    
    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }
    
    public Double getPrecipitation() {
        return precipitation;
    }
    
    public void setPrecipitation(Double precipitation) {
        this.precipitation = precipitation;
    }
}
