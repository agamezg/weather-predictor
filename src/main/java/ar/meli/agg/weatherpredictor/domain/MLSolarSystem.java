package ar.meli.agg.weatherpredictor.domain;

import java.util.Arrays;

import static ar.meli.agg.weatherpredictor.utils.Constants.*;


public class MLSolarSystem extends SolarSystem {

    private static MLSolarSystem instance;

    private int day;

    public static MLSolarSystem getInstance(){
        if(instance == null){
            instance = new MLSolarSystem();
        }
        return instance;
    }

    private MLSolarSystem(){
        super();
        init();
    }

    @Override
    public void init() {
        this.day = 0;
        Planet betasoide = new Planet(BETASOIDE, new PolarPosition(2000,0), new Speed(3));
        Planet vulcano = new Planet(VULCANO, new PolarPosition(1000,0), new Speed(-5));
        Planet ferengi = new Planet(FERENGI,new PolarPosition(500,0), new Speed(1));
        setPlanets(Arrays.asList(betasoide, vulcano, ferengi));
    }

    public void nextDay(){
        this.day++;
        planets.forEach(Planet::move);
    }

    public Integer getDay() {
        return day;
    }
}
