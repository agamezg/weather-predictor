package ar.meli.agg.weatherpredictor.domain;


public class SolarSystem{

    private Sun sun;

    private Planet betasoide;

    private Planet vulcano;

    private Planet ferengi;

    private Integer day;

    private static SolarSystem instance;

    private SolarSystem(){
        this.sun = new Sun(new Position());
        this.betasoide = new Planet(new Position(2000,0), new Speed(3));
        this.vulcano = new Planet(new Position(1000,0), new Speed(-5));
        this.ferengi = new Planet(new Position(2000,0), new Speed(3));
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
        betasoide.move();
        vulcano.move();
        ferengi.move();
    }

    public int getVulcanoPeriod() {
        return vulcano.getPeriod();
    }

    public WeatherPrediction prediction() {
        return new WeatherPrediction(day,Weather.DROUGHT);
    }
}
