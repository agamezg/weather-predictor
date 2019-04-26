package ar.meli.agg.weatherpredictor.domain;


import java.util.ArrayList;
import java.util.List;

import static ar.meli.agg.weatherpredictor.utils.GeometryCalculator.*;

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
                if (!areAligned(angle, p.getPolarPosition().getAngle())){
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
        return areAligned(planets);
    }

    public boolean areTheSunOutSide() {
        return areContainedInASemicircle(planets);
    }

    public boolean isTheBiggerPerimeter() {
        return false;
    }
}
