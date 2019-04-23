package ar.meli.agg.weatherpredictor.utils;

import ar.meli.agg.weatherpredictor.domain.Element;
import ar.meli.agg.weatherpredictor.domain.Planet;
import ar.meli.agg.weatherpredictor.domain.PolarPosition;
import ar.meli.agg.weatherpredictor.domain.Speed;

import java.util.List;
import java.util.function.Function;

public class GeometryCalculator {

    public static boolean areAligned(List<Element> planets){
        boolean areAligned = true;
        if(planets.size() > 2){
            Element p1 = planets.get(0);
            Element p2 = planets.get(1);
            Function<Element, Double> linealEquation = lineEquation(p1, p2);
            Element pn;
            double result;
            int i = 2;
            while (i < planets.size() && areAligned){
                pn = planets.get(i);
                result = linealEquation.apply(pn);
                if(result != 0){
                    areAligned = false;
                }
                i++;
            }
        }
        return areAligned;
    }

    public static Function<Element, Double> lineEquation(Element p1, Element p2){
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
