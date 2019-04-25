package ar.meli.agg.weatherpredictor.utils;

import ar.meli.agg.weatherpredictor.domain.Element;
import ar.meli.agg.weatherpredictor.domain.Planet;

import java.util.List;
import java.util.function.Function;

public class GeometryCalculator {

    public static boolean areAligned(List<Planet> planets){
        boolean areAligned = true;
        if(planets.size() > 2){
            Element p1 = planets.get(0);
            Element p2 = planets.get(1);
            Function<Element, Double> linealEquation = lineEquation(p1, p2);
            Element pn;
            Double result;
            int i = 2;
            while (i < planets.size() && areAligned){
                pn = planets.get(i);
                result = linealEquation.apply(pn);
                if(!result.isNaN() && result != 0){
                    areAligned = false;
                }
                i++;
            }
        }
        return areAligned;
    }

    public static boolean areAligned(Double angle1, Double angle2) {
        return (angle1 % 180 == angle2 % 180);
    }

    private static Function<Element, Double> lineEquation(Element p1, Element p2){
        Function<Element, Double> lineFunction;
        lineFunction = p -> {
            double y2 = p2.getCartesianPosition().getY();
            double y1 = p1.getCartesianPosition().getY();
            double x2 = p2.getCartesianPosition().getX();
            double x1 = p1.getCartesianPosition().getX();
            double y = p.getCartesianPosition().getY();
            double x = p.getCartesianPosition().getX();
            double m = (y2 - y1)/(x2-x1);
            return m*(x-x1)+y1-y;
        };
        return lineFunction;
    }
}
