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

    @Override
    public void run(String... args) {
        simulate(days);
        setHardRainyDays();
    }

    @Scheduled(cron = "${schedule}")
    public void dailyPredictionJob(){
        log.info("Entrando al job para predecir clima a las " + new Date().toString());
        int perimetersSize = perimeters.size();
        double actualBiggerPerimeter = perimeters.lastKey();
        simulate(1);
        if(perimetersSize < perimeters.size()){
            unsetHardRainyDays(actualBiggerPerimeter);
            setHardRainyDays();
        }
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

    WeatherPrediction predict() {
        WeatherPrediction wp;
        if(mlSolarSystem.areAllAligned()){
            wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.DROUGHT);
        }
        else if (mlSolarSystem.areThePlanetsAligned()){
            wp = new WeatherPrediction(mlSolarSystem.getDay(), Weather.BEAUTIFUL_DAY);
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

    private void simulate(int days){
        WeatherPrediction wp;
        for (int i = 0; i <= days; i++){
            wp = predict();
            mlSolarSystem.nextDay();
            wpRepository.save(wp);
        }
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
                perimeters.put(perimeter, new ArrayList<>(Arrays.asList(day)));
            }
        }
        catch (NotAFigureException nafe){
            log.info(nafe.getMessage());
        }
    }

    private void setHardRainyDays() {
        if(!perimeters.isEmpty()) {
            double biggerPerimeter = perimeters.lastKey();
            List<Integer> hardRainyDays = perimeters.get(biggerPerimeter);
            hardRainyDays.forEach(day -> wpRepository.save(new WeatherPrediction(day, Weather.HARD_RAIN)));
        }
    }

    private void unsetHardRainyDays(double olderBiggerPerimeter) {
        if(!perimeters.isEmpty()){
            List<Integer> olderHardRainyDays = perimeters.get(olderBiggerPerimeter);
            olderHardRainyDays.forEach(day -> wpRepository.save(new WeatherPrediction(day, Weather.RAIN)));
        }
    }
}
