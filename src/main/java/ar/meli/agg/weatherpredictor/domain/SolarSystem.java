package ar.meli.agg.weatherpredictor.domain;

import java.util.Arrays;
import java.util.List;

import static ar.meli.agg.weatherpredictor.utils.Constants.*;

public class SolarSystem{

    private Sun sun;

    List<Planet> planets;

    private Integer day;

    private static SolarSystem instance;

    private SolarSystem(){
        this.sun = new Sun(new PolarPosition());
        Planet betasoide = new Planet(BETASOIDE, new PolarPosition(2000,0), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new PolarPosition(1000,0), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new PolarPosition(500,0), new Speed(1));
        planets = Arrays.asList(betasoide, vulcano, ferengi);
        this.day = 0;
    }

    public static SolarSystem getInstance() {
        if(instance == null){
            instance = new SolarSystem();
        }
        return instance;
    }

    public void nextDay(){
        this.day++;
        planets.forEach(Planet::move);
    }

    public Integer getDay() {
        return day;
    }
}
