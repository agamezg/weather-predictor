package ar.meli.agg.weatherpredictor.domain;

import ar.meli.agg.weatherpredictor.utils.GeometryCalculator;

import java.util.ArrayList;
import java.util.List;

abstract class SolarSystem {

    List<Planet> planets;

    SolarSystem() {
        this.planets = new ArrayList<>();
    }

    public void setPlanets(List<Planet> planets) {
        this.planets.clear();
        this.planets.addAll(planets);
    }

    public boolean areAllAligned() {
        if(!planets.isEmpty()) {
            boolean allAligned = true;
            double angle = planets.get(0).getPolarPosition().getAngle();
            Planet p;
            int i = 0;
            while (i < planets.size() && allAligned) {
                p = planets.get(i);
                if (!GeometryCalculator.areAligned(angle, p.getPolarPosition().getAngle())){
                    allAligned = false;
                }
                i++;
            }
            return allAligned;
        }
        else {
            return false;
        }
    }

    public boolean areThePlanetsAligned() {
        return GeometryCalculator.areAligned(planets);
    }

    public boolean areTheSunInside() {
        //TODO probar que ninguna cemicircunsferencia
        // encierra a todo los puntos
        return false;
    }

    public boolean isTheBiggerPerimeter() {
        return false;
    }
}
