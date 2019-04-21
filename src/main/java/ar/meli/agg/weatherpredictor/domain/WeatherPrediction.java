package ar.meli.agg.weatherpredictor.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "weather_prediction")
public class WeatherPrediction {

    @Id
    @JsonProperty
    @Column
    private Integer day;

    @JsonProperty
    @Column
    private String weather;

    public WeatherPrediction(Integer day, Weather weather) {
        this.day = day;
        this.weather = weather.toString();
    }

    public WeatherPrediction() {
        this.day = 0;
        this.weather = Weather.DROUGHT.toString();
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather.toString();
    }
}
