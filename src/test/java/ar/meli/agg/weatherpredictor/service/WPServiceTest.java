package ar.meli.agg.weatherpredictor.service;

import ar.meli.agg.weatherpredictor.domain.SolarSystem;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class WPServiceTest {

    @Mock
    SolarSystem solarSystem;

    @InjectMocks
    WPService wpService;

    @Before
    public void setUp(){
        solarSystem = SolarSystem.getInstance();
    }

    @Test
    public void predictAllAlign(){

    }

}