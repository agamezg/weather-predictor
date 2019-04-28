package ar.meli.agg.weatherpredictor.service;

import ar.meli.agg.weatherpredictor.domain.MLSolarSystem;
import ar.meli.agg.weatherpredictor.domain.Weather;
import ar.meli.agg.weatherpredictor.domain.WeatherPrediction;
import ar.meli.agg.weatherpredictor.exception.NotAFigureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SimulatorService{

    private final MLSolarSystem mlSolarSystem;

    private final SortedMap<Double, List<Integer>> perimeters;

    private final WPService wpService;

    @Autowired
    public SimulatorService(WPService wpService) {
        this.mlSolarSystem = MLSolarSystem.getInstance();
        this.perimeters = new TreeMap<>();
        this.wpService = wpService;
    }

    private static final Logger log = LoggerFactory.getLogger(WPService.class);

    /*@Override
    public void run(String... args) {
        simulate(days);
        setHardRainyDays();
    }*/

    @Scheduled(cron = "${schedule}")
    public void dailyPredictionJob() {
        log.info("Entrando al job para predecir clima a las " + new Date().toString());
        int perimetersSize = perimeters.size();
        double actualBiggerPerimeter = perimeters.lastKey();
        simulate(1);
        if (perimetersSize < perimeters.size()) {
            unsetOldHardRainyDays(actualBiggerPerimeter);
            setHardRainyDays();
        }
    }

    public void startSimulation(Integer days) {
        initializeMlSystem();
        simulate(days);
        setHardRainyDays();
    }

    private void initializeMlSystem() {
        wpService.clear();
        mlSolarSystem.init();
        perimeters.clear();
    }

    public void simulate(int days) {
        WeatherPrediction wp;
        for (int i = 0; i <= days; i++) {
            wp = predict();
            mlSolarSystem.nextDay();
            wpService.save(wp);
        }
    }

    public WeatherPrediction predict() {
        WeatherPrediction wp;
        if (mlSolarSystem.areAllAligned()) {
            wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.DROUGHT);
        } else if (mlSolarSystem.areThePlanetsAligned()) {
            wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.BEAUTIFUL_DAY);
        } else if (mlSolarSystem.areTheSunInside()) {
            wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.RAIN);
            calculatePerimeter();
        } else {
            wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.CLOUDY);

        }
        return wp;
    }

    private void calculatePerimeter() {
        try {
            int day = mlSolarSystem.getDay();
            double perimeter = mlSolarSystem.getPerimeter();
            if (perimeters.containsKey(perimeter)) {
                List<Integer> days = perimeters.get(perimeter);
                days.add(day);
            } else {
                perimeters.put(perimeter, new ArrayList<>(Arrays.asList(day)));
            }
        } catch (NotAFigureException nafe) {
            log.info(nafe.getMessage());
        }
    }

    private void setHardRainyDays() {
        if (!perimeters.isEmpty()) {
            double biggerPerimeter = perimeters.lastKey();
            List<Integer> hardRainyDays = perimeters.get(biggerPerimeter);
            hardRainyDays.forEach(day -> wpService.save(new WeatherPrediction(day, Weather.HARD_RAIN)));
        }
    }

    private void unsetOldHardRainyDays(double olderBiggerPerimeter) {
        if (!perimeters.isEmpty()) {
            List<Integer> olderHardRainyDays = perimeters.get(olderBiggerPerimeter);
            olderHardRainyDays.forEach(day -> wpService.save(new WeatherPrediction(day, Weather.RAIN)));
        }
    }
}
