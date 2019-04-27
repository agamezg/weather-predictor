package ar.meli.agg.weatherpredictor.utils;

import ar.meli.agg.weatherpredictor.domain.CartesianPosition;
import ar.meli.agg.weatherpredictor.domain.Planet;
import ar.meli.agg.weatherpredictor.domain.PolarPosition;
import ar.meli.agg.weatherpredictor.domain.Speed;
import org.junit.Test;

import java.util.Arrays;

import static ar.meli.agg.weatherpredictor.utils.Constants.*;
import static org.assertj.core.api.Assertions.assertThat;


import static ar.meli.agg.weatherpredictor.utils.GeometryCalculator.*;

public class GeometryCalculatorTest {


    @Test
    public void calculatePerimeter1() {
        Planet betasoide = new Planet(BETASOIDE,
                new CartesianPosition(1,4).toPolarPosition(),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new CartesianPosition(4,2).toPolarPosition(),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new CartesianPosition(1,2).toPolarPosition(),
                new Speed(1));

        double expected = 8.61;

        double actual = calculatePerimeter(Arrays.asList(betasoide, ferengi, vulcano));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void calculatePerimeter2() {
        Planet betasoide = new Planet(BETASOIDE,
                new CartesianPosition(2,2).toPolarPosition(),
                new Speed(3));
        Planet vulcano = new Planet(VULCANO,
                new CartesianPosition(-4,1).toPolarPosition(),
                new Speed(-5));
        Planet ferengi = new Planet(FERENGI,
                new CartesianPosition(-2,-2).toPolarPosition(),
                new Speed(1));

        double expected = 15.35;

        double actual = calculatePerimeter(Arrays.asList(betasoide, ferengi, vulcano));

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void subsAnglesNegative() {
        double a1 = 30;
        double a2 = 180;

        double expected = 210;

        double actual = subsDegrees(a1, a2);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void subsAnglesPositive() {
        double a1 = 350;
        double a2 = -50;

        double expected = 40;

        double actual = subsDegrees(a1, a2);

        assertThat(expected).isEqualTo(actual);
    }
}