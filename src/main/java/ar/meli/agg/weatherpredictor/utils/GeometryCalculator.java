package ar.meli.agg.weatherpredictor.utils;

import ar.meli.agg.weatherpredictor.domain.CartesianPosition;
import ar.meli.agg.weatherpredictor.domain.Element;
import ar.meli.agg.weatherpredictor.domain.Planet;

import java.text.DecimalFormat;
import java.util.Comparator;
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

    public static boolean areContainedInASemicircle(List<Planet> planets) {
        boolean areContained;
        planets.sort(Comparator.comparingDouble(p -> p.getPolarPosition().getAngle()));
        Double smallestAngle = planets.get(0).getPolarPosition().getAngle();
        Double semicircleAngle = sumDegrees(smallestAngle, 180D);

        areContained = upperSemicircle(planets, semicircleAngle);

        if(!areContained){
            return !bellowSemicircle(planets, smallestAngle);
        }
        return true;
    }

    private static boolean bellowSemicircle(List<Planet> planets, Double smallestAngle) {
        int i = 1;
        Double semicircle = subsDegrees(smallestAngle, 180D);
        Double angle2;
        while (i < planets.size()){
            angle2 = planets.get(i).getPolarPosition().getAngle();
            if(semicircle > angle2){
                return true;
            }
            i++;
        }
        return false;
    }

    private static boolean upperSemicircle(List<Planet> planets, Double semicircle) {
        boolean areContained = true;
        Double angle;
        int i = 1;
        while (i < planets.size() && areContained){
            angle = planets.get(i).getPolarPosition().getAngle();
            if(angle > semicircle){
                areContained = false;
            }
            i++;
        }
        return areContained;
    }

    public static Double sumDegrees(Double a1, Double a2){

        double a = a1 + a2;
        if(a > 360) {
            a = a - 360;
        }
        else if(a < 0){
            a = a + 360;
        }
        return a;
    }

    public static Double subsDegrees(Double a1, Double a2){

        double a = a1 - a2;
        if(a > 360) {
            a = a - 360;
        }
        else if(a < 0){
            a = 360 - Math.abs(a);
        }
        return a;
    }

    public static double calculatePerimeter(List<Planet> planets) {
        DecimalFormat format = new DecimalFormat("##.##");
        double perimeter = 0;
        CartesianPosition point1;
        CartesianPosition point2;
        int nextIndex;
        int planetsCount = planets.size();
        for (int i = 0; i < planetsCount; i++) {
            nextIndex = i == planetsCount -1 ? 0 : i + 1;
            point1 = planets.get(i).getCartesianPosition();
            point2 = planets.get(nextIndex).getCartesianPosition();

            perimeter += calculateDistance(point1, point2);
        }
        return Math.round(perimeter*100.0)/100.0;
    }

    private static double calculateDistance(CartesianPosition point1, CartesianPosition point2) {
        return Math.sqrt(Math.pow(point2.getY()-point1.getY(),2) + Math.pow(point2.getX()-point1.getX(),2));
    }
}
