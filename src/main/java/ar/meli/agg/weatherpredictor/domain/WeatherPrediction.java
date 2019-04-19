package ar.meli.agg.weatherpredictor.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class WeatherPrediction {

    @Id
    @JsonProperty
    private Integer day;

    @JsonProperty
    private Weather weather;

    public WeatherPrediction(Integer day, Weather weather) {
        this.day = day;
        this.weather = weather;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }
}
