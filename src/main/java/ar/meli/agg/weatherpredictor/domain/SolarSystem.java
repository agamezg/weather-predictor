package ar.meli.agg.weatherpredictor.domain;

import ar.meli.agg.weatherpredictor.utils.GeometryCalculator;

import java.util.ArrayList;
import java.util.Arrays;
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
        List<Element> elements = new ArrayList<>(planets);
        elements.add(sun);
        return GeometryCalculator.areAligned(elements);
    }

    public boolean areThePlanetsAligned() {
        return false;
    }
}
