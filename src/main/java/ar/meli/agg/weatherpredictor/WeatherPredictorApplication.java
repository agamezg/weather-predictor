package ar.meli.agg.weatherpredictor;

import ar.meli.agg.weatherpredictor.domain.Weather;
import ar.meli.agg.weatherpredictor.domain.WeatherPrediction;
import ar.meli.agg.weatherpredictor.service.WPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeatherPredictorApplication implements CommandLineRunner {

    private final WPService wpService;

    @Autowired
    public WeatherPredictorApplication(WPService wpService) {
        this.wpService = wpService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherPredictorApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        /*WeatherPrediction weatherPrediction = new WeatherPrediction(1, Weather.BEAUTIFULL_DAY);
        wpService.save(weatherPrediction);*/
    }
}
