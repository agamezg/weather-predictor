package ar.meli.agg.weatherpredictor.controller;

import ar.meli.agg.weatherpredictor.domain.Weather;
import ar.meli.agg.weatherpredictor.domain.WeatherPrediction;
import ar.meli.agg.weatherpredictor.service.WPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/wp-service")
public class WPController {

    private final WPService wpService;

    @Autowired
    public WPController(WPService wpService) {
        this.wpService = wpService;
    }

    @GetMapping("/weather")
    ResponseEntity<?> getWeather(@RequestParam(value = "day") Integer day){
        ResponseEntity<?> response;
        WeatherPrediction weatherPrediction = wpService.find(day);
        if(weatherPrediction != null)
            response = ResponseEntity.ok(weatherPrediction);
        else
            response = ResponseEntity.notFound().build();
        return response;
    }

    @GetMapping("/drought-days")
    ResponseEntity<?> getDrouhtDays(){
        ResponseEntity<?> response;
        Map droughtDays = wpService.countDaysByWeather(Weather.DROUGHT);
        response = ResponseEntity.ok(droughtDays);
        return response;
    }

    @GetMapping("/rainy-days")
    ResponseEntity<?> getRainyDays(){
        ResponseEntity<?> response;
        Map droughtDays = wpService.countDaysByWeather(Weather.RAIN);
        response = ResponseEntity.ok(droughtDays);
        return response;
    }

    @GetMapping("/beautiful-days")
    ResponseEntity<?> getBeautifulDays() {
        ResponseEntity<?> response;
        Map droughtDays = wpService.countDaysByWeather(Weather.BEAUTIFUL_DAY);
        response = ResponseEntity.ok(droughtDays);
        return response;
    }

    @GetMapping("/cloudy-days")
    ResponseEntity<?> getCloudyDays() {
        ResponseEntity<?> response;
        Map droughtDays = wpService.countDaysByWeather(Weather.CLOUDY);
        response = ResponseEntity.ok(droughtDays);
        return response;
    }

    @GetMapping("/hardRainy-days")
    ResponseEntity<?> getHardRainyDays() {
        ResponseEntity<?> response;
        Map droughtDays = wpService.countDaysByWeather(Weather.HARD_RAIN);
        response = ResponseEntity.ok(droughtDays);
        return response;
    }


}
