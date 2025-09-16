package com.sandbox.javaweatherapp.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherResponse {
    
    @JsonProperty("hourly")
    private HourlyWeather hourly;
    
    @JsonProperty("daily")
    private DailyWeather daily;
    
    // Constructors
    public WeatherResponse() {}
    
    public WeatherResponse(HourlyWeather hourly, DailyWeather daily) {
        this.hourly = hourly;
        this.daily = daily;
    }
    
    // Getters and Setters
    public HourlyWeather getHourly() {
        return hourly;
    }
    
    public void setHourly(HourlyWeather hourly) {
        this.hourly = hourly;
    }
    
    public DailyWeather getDaily() {
        return daily;
    }
    
    public void setDaily(DailyWeather daily) {
        this.daily = daily;
    }
    
    // Inner classes for nested JSON structure
    public static class HourlyWeather {
        @JsonProperty("time")
        private List<String> time;
        
        @JsonProperty("temperature_2m")
        private List<Double> temperature;
        
        @JsonProperty("weather_code")
        private List<Integer> weatherCode;
        
        @JsonProperty("relative_humidity_2m")
        private List<Integer> humidity;
        
        @JsonProperty("wind_speed_10m")
        private List<Double> windSpeed;
        
        @JsonProperty("precipitation")
        private List<Double> precipitation;
        
        // Getters and Setters
        public List<String> getTime() { return time; }
        public void setTime(List<String> time) { this.time = time; }
        
        public List<Double> getTemperature() { return temperature; }
        public void setTemperature(List<Double> temperature) { this.temperature = temperature; }
        
        public List<Integer> getWeatherCode() { return weatherCode; }
        public void setWeatherCode(List<Integer> weatherCode) { this.weatherCode = weatherCode; }
        
        public List<Integer> getHumidity() { return humidity; }
        public void setHumidity(List<Integer> humidity) { this.humidity = humidity; }
        
        public List<Double> getWindSpeed() { return windSpeed; }
        public void setWindSpeed(List<Double> windSpeed) { this.windSpeed = windSpeed; }
        
        public List<Double> getPrecipitation() { return precipitation; }
        public void setPrecipitation(List<Double> precipitation) { this.precipitation = precipitation; }
    }
    
    public static class DailyWeather {
        @JsonProperty("time")
        private List<String> time;
        
        @JsonProperty("temperature_2m_max")
        private List<Double> temperatureMax;
        
        @JsonProperty("temperature_2m_min")
        private List<Double> temperatureMin;
        
        @JsonProperty("weather_code")
        private List<Integer> weatherCode;
        
        @JsonProperty("precipitation_sum")
        private List<Double> precipitation;
        
        // Getters and Setters
        public List<String> getTime() { return time; }
        public void setTime(List<String> time) { this.time = time; }
        
        public List<Double> getTemperatureMax() { return temperatureMax; }
        public void setTemperatureMax(List<Double> temperatureMax) { this.temperatureMax = temperatureMax; }
        
        public List<Double> getTemperatureMin() { return temperatureMin; }
        public void setTemperatureMin(List<Double> temperatureMin) { this.temperatureMin = temperatureMin; }
        
        public List<Integer> getWeatherCode() { return weatherCode; }
        public void setWeatherCode(List<Integer> weatherCode) { this.weatherCode = weatherCode; }
        
        public List<Double> getPrecipitation() { return precipitation; }
        public void setPrecipitation(List<Double> precipitation) { this.precipitation = precipitation; }
    }
}
