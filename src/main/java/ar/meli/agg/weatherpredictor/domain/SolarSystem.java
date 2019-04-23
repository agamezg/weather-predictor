package ar.meli.agg.weatherpredictor.domain;

import java.util.ArrayList;
import java.util.List;

class SolarSystem {

    Sun sun;

    List<Planet> planets;

    SolarSystem() {
        this.sun = new Sun(new PolarPosition());
        this.planets = new ArrayList<>();
    }

    public void setPlanets(List<Planet> planets) {
        this.planets.clear();
        this.planets.addAll(planets);
    }

    public boolean areAllAligned() {
        return true;
    }

    public boolean areThePlanetsAligned() {
        return false;
    }
}
