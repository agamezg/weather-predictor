package ar.meli.agg.weatherpredictor.utils;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;


import static ar.meli.agg.weatherpredictor.utils.GeometryCalculator.subsDegrees;

public class GeometryCalculatorTest {


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