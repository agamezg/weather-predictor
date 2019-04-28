package ar.meli.agg.weatherpredictor.domain;


import ar.meli.agg.weatherpredictor.exception.NotAFigureException;
import ar.meli.agg.weatherpredictor.utils.GeometryCalculator;

import java.util.ArrayList;
import java.util.List;

import static ar.meli.agg.weatherpredictor.utils.GeometryCalculator.areAligned;
import static ar.meli.agg.weatherpredictor.utils.GeometryCalculator.areInsideTheTriangle;

abstract class SolarSystem {

    Sun sun;

    List<Planet> planets;

    SolarSystem() {
        this.sun = new Sun(new PolarPosition(0,0));
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

    public double getPerimeter() throws NotAFigureException {
        double perimeter;
        if(planets.size() >= 3 && !areAllAligned() && !areThePlanetsAligned()){
            perimeter = GeometryCalculator.calculatePerimeter(planets);
        }
        else {
            throw new NotAFigureException();
        }
        return perimeter;
    }

    public boolean areTheSunInside() {
        return areInsideTheTriangle(planets, sun);
    }

    public abstract void init();
}
