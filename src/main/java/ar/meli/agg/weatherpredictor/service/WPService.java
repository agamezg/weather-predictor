package ar.meli.agg.weatherpredictor.service;

import ar.meli.agg.weatherpredictor.domain.Weather;
import ar.meli.agg.weatherpredictor.domain.WeatherPrediction;
import ar.meli.agg.weatherpredictor.repository.WPRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WPService {

    private final WPRepository wpRepository;

    @Autowired
    public WPService(WPRepository wpRepository) {
        this.wpRepository = wpRepository;
    }

    public WeatherPrediction find(Integer day) {
        WeatherPrediction weatherPrediction = null;
        if (wpRepository.findById(day).isPresent())
            weatherPrediction = wpRepository.findById(day).get();
        return weatherPrediction;
    }

    public Map countDaysByWeather(Weather weather) {
        HashMap<String, Integer> droughtDays = new HashMap<>();
        int count = wpRepository.countAllByWeatherEquals(weather.toString());
        droughtDays.put(weather.toString(), count);
        return droughtDays;
    }

    public void save(WeatherPrediction wp) {
        this.wpRepository.save(wp);
    }

    public void clear() {
        this.wpRepository.deleteAll();
    }
}
