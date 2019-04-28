package ar.meli.agg.weatherpredictor.service;

import ar.meli.agg.weatherpredictor.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static ar.meli.agg.weatherpredictor.utils.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class WPServiceTest {

    @Mock
    MLSolarSystem mlSolarSystem;

    @Mock
    SortedMap perimeters;

    @InjectMocks
    WPService wpService;

    @Before
    public void setUp(){
        mlSolarSystem = MLSolarSystem.getInstance();
        perimeters = new TreeMap<Double, List<Integer>>();
    }

    @Test
    public void predictAllAlignSameAngleWeatherDrought(){

        Planet betasoide = new Planet(BETASOIDE,
                new PolarPosition(2000,30),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new PolarPosition(1000,30),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new PolarPosition(500,30),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.DROUGHT);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictAllAlignSameAngleWeatherDrought_2(){

        Planet betasoide = new Planet(BETASOIDE,
                new PolarPosition(2000,90),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new PolarPosition(1000,90),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new PolarPosition(500,90),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.DROUGHT);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());    }

    @Test
    public void predictAllAlignDifferentAngleWeatherDrought(){

        Planet betasoide = new Planet(BETASOIDE,
                new PolarPosition(2000,30),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new PolarPosition(1000,210),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new PolarPosition(500,210),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.DROUGHT);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());    }

    @Test
    public void predictAllAlignDifferentAngleWeatherDrought_2(){

        Planet betasoide = new Planet(BETASOIDE,
                new PolarPosition(2000,60),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new PolarPosition(1000,240),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new PolarPosition(500,60),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.DROUGHT);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictPlanetsAlignHorizontallyBeautifulDay(){

        Planet betasoide = new Planet(BETASOIDE,
                new CartesianPosition(6,8).toPolarPosition(),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new CartesianPosition(8,8).toPolarPosition(),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new CartesianPosition(9,8).toPolarPosition(),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.BEAUTIFULL_DAY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictPlanetsAlignVerticallyBeautifulDay(){

        Planet betasoide = new Planet(BETASOIDE,
                new CartesianPosition(6,5).toPolarPosition(),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new CartesianPosition(6,9).toPolarPosition(),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new CartesianPosition(6,3).toPolarPosition(),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.BEAUTIFULL_DAY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictPlanetsAlignDiagonallyBeautifulDay(){

        Planet betasoide = new Planet(BETASOIDE,
                new CartesianPosition(4,3).toPolarPosition(),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new CartesianPosition(5,7).toPolarPosition(),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new CartesianPosition(1,-9).toPolarPosition(),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.BEAUTIFULL_DAY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictPlanetsAlignDiagonallyBeautifulDay_2(){

        Planet betasoide = new Planet(BETASOIDE,
                new CartesianPosition(4,3).toPolarPosition(),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new CartesianPosition(5,7).toPolarPosition(),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new CartesianPosition(0,-13).toPolarPosition(),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.BEAUTIFULL_DAY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictPlanetsTriangleSunOutSide(){

        Planet betasoide = new Planet(BETASOIDE,
                new PolarPosition(2000, 25),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new PolarPosition(1000, 30),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new PolarPosition(500, 60),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.CLOUDY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictPlanetsTriangleSunOutSide_2(){

        Planet betasoide = new Planet(BETASOIDE,
                new PolarPosition(2000, 178),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new PolarPosition(1000, 179),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new PolarPosition(500, 190),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.CLOUDY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictPlanetsTriangleSunOutSide_3(){

        Planet betasoide = new Planet(BETASOIDE,
                new PolarPosition(2000, 1),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new PolarPosition(1000, 358),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new PolarPosition(500, 359),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.CLOUDY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictPlanetsTriangleSunOutSide_4(){

        Planet betasoide = new Planet(BETASOIDE,
                new PolarPosition(2000, 357),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new PolarPosition(1000, 358),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new PolarPosition(500, 359),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.CLOUDY);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictPlanetsTriangleSunInSide(){

        Planet betasoide = new Planet(BETASOIDE,
                new PolarPosition(2000, 280),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new PolarPosition(1000, 120),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new PolarPosition(500, 30),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.RAIN);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictPlanetsTriangleSunInside_2(){

        Planet betasoide = new Planet(BETASOIDE,
                new PolarPosition(2000, 240),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new PolarPosition(1000, 85),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new PolarPosition(500, 290),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.RAIN);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }

    @Test
    public void predictPlanetsTriangleSunInsideBiggerPerimeter(){

        Planet betasoide = new Planet(BETASOIDE,
                new PolarPosition(2000, 240),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new PolarPosition(1000, 85),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new PolarPosition(500, 290),
                new Speed(1));

        mlSolarSystem.setPlanets(Arrays.asList(betasoide, vulcano, ferengi));

        WeatherPrediction expectedWeather = new WeatherPrediction(0, Weather.RAIN);

        WeatherPrediction weatherPrediction = wpService.predict();

        assertThat(weatherPrediction.getWeather()).isEqualTo(expectedWeather.getWeather());
    }
}