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
        private List<Double> temperature_2m;
        
        @JsonProperty("weather_code")
        private List<Integer> weather_code;
        
        @JsonProperty("relative_humidity_2m")
        private List<Integer> relative_humidity_2m;
        
        @JsonProperty("wind_speed_10m")
        private List<Double> wind_speed_10m;
        
        @JsonProperty("precipitation")
        private List<Double> precipitation;
        
        // Getters and Setters
        public List<String> getTime() { return time; }
        public void setTime(List<String> time) { this.time = time; }
        
        public List<Double> getTemperature_2m() { return temperature_2m; }
        public void setTemperature_2m(List<Double> temperature_2m) { this.temperature_2m = temperature_2m; }
        
        public List<Integer> getWeather_code() { return weather_code; }
        public void setWeather_code(List<Integer> weather_code) { this.weather_code = weather_code; }
        
        public List<Integer> getRelative_humidity_2m() { return relative_humidity_2m; }
        public void setRelative_humidity_2m(List<Integer> relative_humidity_2m) { this.relative_humidity_2m = relative_humidity_2m; }
        
        public List<Double> getWind_speed_10m() { return wind_speed_10m; }
        public void setWind_speed_10m(List<Double> wind_speed_10m) { this.wind_speed_10m = wind_speed_10m; }
        
        public List<Double> getPrecipitation() { return precipitation; }
        public void setPrecipitation(List<Double> precipitation) { this.precipitation = precipitation; }
    }
    
    public static class DailyWeather {
        @JsonProperty("time")
        private List<String> time;
        
        @JsonProperty("temperature_2m_max")
        private List<Double> temperature_2m_max;
        
        @JsonProperty("temperature_2m_min")
        private List<Double> temperature_2m_min;
        
        @JsonProperty("weather_code")
        private List<Integer> weather_code;
        
        @JsonProperty("precipitation_sum")
        private List<Double> precipitation_sum;
        
        // Getters and Setters
        public List<String> getTime() { return time; }
        public void setTime(List<String> time) { this.time = time; }
        
        public List<Double> getTemperature_2m_max() { return temperature_2m_max; }
        public void setTemperature_2m_max(List<Double> temperature_2m_max) { this.temperature_2m_max = temperature_2m_max; }
        
        public List<Double> getTemperature_2m_min() { return temperature_2m_min; }
        public void setTemperature_2m_min(List<Double> temperature_2m_min) { this.temperature_2m_min = temperature_2m_min; }
        
        public List<Integer> getWeather_code() { return weather_code; }
        public void setWeather_code(List<Integer> weather_code) { this.weather_code = weather_code; }
        
        public List<Double> getPrecipitation_sum() { return precipitation_sum; }
        public void setPrecipitation_sum(List<Double> precipitation_sum) { this.precipitation_sum = precipitation_sum; }
    }
}
