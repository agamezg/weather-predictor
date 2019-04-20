package ar.meli.agg.weatherpredictor.service;

import ar.meli.agg.weatherpredictor.domain.Weather;
import ar.meli.agg.weatherpredictor.repository.WPRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class WPServiceTest {

    @Mock
    WPRepository wpRepository;

    @InjectMocks
    WPService wpService;

    @Test
    public void predictWeatherDay0(){

        Integer day = 0;
        Weather expectedWeather = Weather.DROUGHT;

        Weather weather = wpService.predict(day);

        assertThat(expectedWeather).isEqualTo(weather);
    }

    @Test
    public void predictWeatherDay1(){

        Integer day = 1;
        Weather expectedWeather = Weather.CLOUDY;

        Weather weather = wpService.predict(day);

        assertThat(expectedWeather).isEqualTo(weather);
    }

    @Test
    public void predictWeatherDay2(){

        Integer day = 2;
        Weather expectedWeather = Weather.CLOUDY;

        Weather weather = wpService.predict(day);

        assertThat(expectedWeather).isEqualTo(weather);
    }
}