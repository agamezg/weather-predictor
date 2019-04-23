package ar.meli.agg.weatherpredictor.domain;

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

    public void moveAngle(long angle) {
        double moved = this.angle+angle;

        if(moved > 360) {
            moved = moved - 360;
        }
        else if(moved < 0){
            moved = 360 + moved;
        }

        this.angle = moved;
    }

    public void setAngle(double angle){
        this.angle = angle;
    }
}
