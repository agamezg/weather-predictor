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
    public void predictAllAlignSameAngleWeatherDrought(){

        Planet betasoide = new Planet(BETASOIDE, new PolarPosition(2000,30), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new PolarPosition(1000,30), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new PolarPosition(500,30), new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.DROUGHT);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(expectedWeather.getWeather()).isEqualTo(weatherPrediction.getWeather());
    }

    @Test
    public void predictAllAlignSameAngleWeatherDrought_2(){

        Planet betasoide = new Planet(BETASOIDE, new PolarPosition(2000,90), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new PolarPosition(1000,90), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new PolarPosition(500,90), new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.DROUGHT);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(expectedWeather.getWeather()).isEqualTo(weatherPrediction.getWeather());
    }

    @Test
    public void predictAllAlignDifferentAngleWeatherDrought(){

        Planet betasoide = new Planet(BETASOIDE, new PolarPosition(2000,30), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new PolarPosition(1000,210), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new PolarPosition(500,210), new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.DROUGHT);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(expectedWeather.getWeather()).isEqualTo(weatherPrediction.getWeather());
    }

    @Test
    public void predictAllAlignDifferentAngleWeatherDrought_2(){

        Planet betasoide = new Planet(BETASOIDE, new PolarPosition(2000,60), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new PolarPosition(1000,240), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new PolarPosition(500,60), new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.DROUGHT);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(expectedWeather.getWeather()).isEqualTo(weatherPrediction.getWeather());
    }

    @Test
    public void predictPlanetsAlignHorizontallyBeautifulDay(){

        Planet betasoide = new Planet(BETASOIDE, new CartesianPosition(6,8).toPolarPosition(), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new CartesianPosition(8,8).toPolarPosition(), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new CartesianPosition(9,8).toPolarPosition(), new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.BEAUTIFULL_DAY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(expectedWeather.getWeather()).isEqualTo(weatherPrediction.getWeather());
    }

    @Test
    public void predictPlanetsAlignVerticallyBeautifulDay(){

        Planet betasoide = new Planet(BETASOIDE, new CartesianPosition(6,5).toPolarPosition(), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new CartesianPosition(6,9).toPolarPosition(), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new CartesianPosition(6,3).toPolarPosition(), new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.BEAUTIFULL_DAY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(expectedWeather.getWeather()).isEqualTo(weatherPrediction.getWeather());
    }

    @Test
    public void predictPlanetsAlignDiagonallyBeautifulDay(){

        Planet betasoide = new Planet(BETASOIDE, new CartesianPosition(4,3).toPolarPosition(), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new CartesianPosition(5,7).toPolarPosition(), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new CartesianPosition(1,-9).toPolarPosition(), new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.BEAUTIFULL_DAY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(expectedWeather.getWeather()).isEqualTo(weatherPrediction.getWeather());
    }

    @Test
    public void predictPlanetsAlignDiagonallyBeautifulDay_2(){

        Planet betasoide = new Planet(BETASOIDE, new CartesianPosition(4,3).toPolarPosition(), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new CartesianPosition(5,7).toPolarPosition(), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new CartesianPosition(0,-13).toPolarPosition(), new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.BEAUTIFULL_DAY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(expectedWeather.getWeather()).isEqualTo(weatherPrediction.getWeather());
    }


    /*@Test
    public void toolTest(){
        Planet betasoide = new Planet(BETASOIDE, new CartesianPosition(5,5).toPolarPosition(), new Speed(3));
        Planet feing = new Planet(VULCANO, new CartesianPosition(10,10).toPolarPosition(), new Speed(-5));
        Planet vulcano = new Planet(VULCANO, new CartesianPosition(15,10).toPolarPosition(), new Speed(-5));
        assertThat(GeometryCalculator.areAligned(Arrays.asList(betasoide,vulcano,feing))).isTrue();
    }*/

}