package ar.meli.agg.weatherpredictor.service;

import ar.meli.agg.weatherpredictor.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static ar.meli.agg.weatherpredictor.utils.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class WPServiceTest {

    @Mock
    MLSolarSystem mlSolarSystem;

    @InjectMocks
    WPService wpService;

    @Before
    public void setUp(){
        mlSolarSystem = MLSolarSystem.getInstance();
    }

    @Test
    public void predictAllAlignSameAngle(){

        Planet betasoide = new Planet(BETASOIDE, new PolarPosition(2000,30), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new PolarPosition(1000,30), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new PolarPosition(500,30), new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.DROUGHT);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(expectedWeather.getWeather()).isEqualTo(weatherPrediction.getWeather());
    }

    @Test
    public void predictAllAlignDiferentAngle(){

        Planet betasoide = new Planet(BETASOIDE, new PolarPosition(2000,30), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new PolarPosition(1000,210), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new PolarPosition(500,210), new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.DROUGHT);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(expectedWeather.getWeather()).isEqualTo(weatherPrediction.getWeather());
    }



}