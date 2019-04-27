package ar.meli.agg.weatherpredictor.service;

import ar.meli.agg.weatherpredictor.domain.MLSolarSystem;
import ar.meli.agg.weatherpredictor.domain.Weather;
import ar.meli.agg.weatherpredictor.domain.WeatherPrediction;
import ar.meli.agg.weatherpredictor.exception.NotAFigureException;
import ar.meli.agg.weatherpredictor.repository.WPRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class WPService implements CommandLineRunner {

    @Value("${simulate.days}")
    private int days;

    private final WPRepository wpRepository;

    private final MLSolarSystem mlSolarSystem;

    private final HashMap perimeters;

    private static Logger log = LoggerFactory.getLogger(WPService.class);

    @Autowired
    public WPService(WPRepository wpRepository) {
        this.wpRepository = wpRepository;
        this.mlSolarSystem = MLSolarSystem.getInstance();
        this.perimeters = new HashMap<Integer, Double>();
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
            mlSolarSystem.nextDay();
            wpRepository.save(wp);
        }
    }

    WeatherPrediction predict() {
        WeatherPrediction wp;
        if(mlSolarSystem.areAllAligned()){
            wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.DROUGHT);
        }
        else if (mlSolarSystem.areThePlanetsAligned()){
            wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.BEAUTIFULL_DAY);
        }
        else if(mlSolarSystem.areTheSunOutside()){
            wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.CLOUDY);
        }
        else {
            if(isBiggerPerimeter())
                wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.HARD_RAIN);
            else
                wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.RAIN);
        }
        return wp;
    }

    private boolean isBiggerPerimeter() {
        try {
            boolean isBigger = false;
            double perimeter = mlSolarSystem.getPerimeter();
            return isBigger;
        }
        catch (NotAFigureException nafe){
            return false;
        }
    }

    @Scheduled(cron = "${schedule}")
    public void dailyPredictionJob(){
        log.info("entrando al job para predecir clima a las " + new Date().toString());
        simulate(1);
    }

    @Override
    public void run(String... args) {
        simulate(1);
        simulate(days);
    }
}
