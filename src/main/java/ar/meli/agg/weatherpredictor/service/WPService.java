package ar.meli.agg.weatherpredictor.service;

import ar.meli.agg.weatherpredictor.domain.Weather;
import ar.meli.agg.weatherpredictor.domain.WeatherPrediction;
import ar.meli.agg.weatherpredictor.repository.WPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WPService {

    private final WPRepository wpRepository;

    @Autowired
    public WPService(WPRepository wpRepository) {
        this.wpRepository = wpRepository;
    }

    public WeatherPrediction find(Integer day) {
        WeatherPrediction weatherPrediction = null;
        if(wpRepository.findById(day).isPresent())
            weatherPrediction = wpRepository.findById(day).get();
        return weatherPrediction;
    }

    public void save(WeatherPrediction weatherPrediction) {
        if(weatherPrediction != null)
            this.wpRepository.save(weatherPrediction);
    }

    public void deleteAll() {
        wpRepository.deleteAll();
    }

    public Weather predict(Integer day) {
        if(day.compareTo(0) == 0)
            return Weather.DROUGHT;
        else
            return Weather.CLOUDY;
    }
}
