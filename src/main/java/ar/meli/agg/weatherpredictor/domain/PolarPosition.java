package ar.meli.agg.weatherpredictor.domain;

import static ar.meli.agg.weatherpredictor.utils.GeometryCalculator.sumDegrees;

public class PolarPosition {

    private double radius;

    private double angle;

    public PolarPosition(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
    }

    public PolarPosition() {
        this.radius = 0;
        this.angle = 0;
    }

    public double getRadius() {
        return radius;
    }

    public double getAngle() {
        return angle;
    }

    public CartesianPosition toCartesianPosition(){
        double radians = Math.toRadians(this.angle);
        double x = Math.round(radius*Math.cos(radians));
        double y = Math.round(radius*Math.sin(radians));
        return new CartesianPosition(x,y);
    }

    public void moveAngle(double angle) {
        this.angle = sumDegrees(this.angle, angle);
    }

    public void setAngle(double angle){
        this.angle = angle;
    }
}
