package ar.meli.agg.weatherpredictor.domain;

import static ar.meli.agg.weatherpredictor.utils.GeometryCalculator.sumDegrees;

public class PolarPosition {

    private double radius;

    private double angle;

    public PolarPosition(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
    }

    CartesianPosition toCartesianPosition(){
        double radians = Math.toRadians(this.angle);
        double x = Math.round(radius*Math.cos(radians));
        double y = Math.round(radius*Math.sin(radians));
        return new CartesianPosition(x,y);
    }

    void moveAngle(double angle) {
        this.angle = sumDegrees(this.angle, angle);
    }

    public double getAngle() {
        return angle;
    }
}
