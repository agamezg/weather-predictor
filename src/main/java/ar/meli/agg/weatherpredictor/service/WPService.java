package ar.meli.agg.weatherpredictor.service;

import ar.meli.agg.weatherpredictor.domain.SolarSystem;
import ar.meli.agg.weatherpredictor.domain.Weather;
import ar.meli.agg.weatherpredictor.domain.WeatherPrediction;
import ar.meli.agg.weatherpredictor.repository.WPRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class WPService implements CommandLineRunner {

    @Value("${simulate.days}")
    private int days;

    private final WPRepository wpRepository;

    private final SolarSystem solarSystem;

    private static Logger log = LoggerFactory.getLogger(WPService.class);

    @Autowired
    public WPService(WPRepository wpRepository) {
        this.wpRepository = wpRepository;
        this.solarSystem = SolarSystem.getInstance();
    }

    public WeatherPrediction find(Integer day) {
        WeatherPrediction weatherPrediction = null;
        if (wpRepository.findById(day).isPresent())
            weatherPrediction = wpRepository.findById(day).get();
        return weatherPrediction;
    }

    private void simulate(int days){
        WeatherPrediction wp;
        for (int i = 0; i < days; i++){
            wp = predict();
            wpRepository.save(wp);
            solarSystem.nextDay();
        }
    }

    public WeatherPrediction predict() {
        return new WeatherPrediction(solarSystem.getDay(), Weather.DROUGHT);
    }

    @Scheduled(cron = "${schedule}")
    public void dailyPredictionJob(){
        log.info("entrando al job para predecir clima a las " + new Date().toString());
        simulate(1);
    }

    @Override
    public void run(String... args) throws Exception {
        simulate(1);
        simulate(days);
    }
}
