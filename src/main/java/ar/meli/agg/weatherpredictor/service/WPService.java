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

import java.util.*;

@Service
public class WPService implements CommandLineRunner {

    @Value("${simulate.days}")
    private int days;

    private final WPRepository wpRepository;

    private final MLSolarSystem mlSolarSystem;

    private final SortedMap<Double, List<Integer>> perimeters;

    private static Logger log = LoggerFactory.getLogger(WPService.class);

    @Autowired
    public WPService(WPRepository wpRepository) {
        this.wpRepository = wpRepository;
        this.mlSolarSystem = MLSolarSystem.getInstance();
        this.perimeters = new TreeMap<>();
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
        setHardRainyDays();
    }

    private void setHardRainyDays() {
        if(!perimeters.isEmpty()) {
            double biggerPerimeter = perimeters.lastKey();
            List<Integer> hardRainyDays = perimeters.get(biggerPerimeter);
            hardRainyDays.forEach(day -> wpRepository.save(new WeatherPrediction(day, Weather.HARD_RAIN)));
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
            wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.RAIN);
            calculatePerimeter();
        }
        return wp;
    }

    private void calculatePerimeter() {
        try {
            int day = mlSolarSystem.getDay();
            double perimeter = mlSolarSystem.getPerimeter();
            if(perimeters.containsKey(perimeter)){
                List<Integer> days = perimeters.get(perimeter);
                days.add(day);
            }
            else {
                perimeters.put(perimeter, Collections.singletonList(day));
            }
        }
        catch (NotAFigureException nafe){
            log.info(nafe.getMessage());
        }
    }

    private boolean isBiggerPerimeter() {
        /*try {
            boolean isBigger = false;
            double perimeter = mlSolarSystem.getPerimeter();
            Object[] perimetersArray = perimeters.keySet().toArray();
            int length = perimetersArray.length;
            if(perimeters.values().toArray()[length -1] < perimeter)
            return isBigger;
        }
        catch (NotAFigureException nafe){
            return false;
        }*/

        return false;
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
