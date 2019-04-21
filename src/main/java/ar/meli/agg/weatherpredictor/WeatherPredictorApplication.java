package ar.meli.agg.weatherpredictor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherPredictorApplication{

    public static void main(String[] args) {
        SpringApplication.run(WeatherPredictorApplication.class, args);
    }
}
