package ar.meli.agg.weatherpredictor.domain;

public class PolarPosition {

    private long radius;

    private long angle;

    public PolarPosition(long radius, long angle) {
        this.radius = radius;
        this.angle = angle;
    }

    public PolarPosition() {
        this.radius = 0;
        this.angle = 0;
    }

    public long getRadius() {
        return radius;
    }

    public long getAngle() {
        return angle;
    }

    public CartesianPosition getCartesianPosition(){
        double x = radius*Math.cos(angle);
        double y = radius*Math.sin(angle);
        return new CartesianPosition(x,y);
    }

    public void increaseAngle(long angle) {
        this.angle += angle;
    }
}
