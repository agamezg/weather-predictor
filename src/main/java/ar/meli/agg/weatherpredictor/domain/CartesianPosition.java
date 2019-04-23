package ar.meli.agg.weatherpredictor.domain;

public class CartesianPosition {

    private double x;

    private double y;

    public CartesianPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public CartesianPosition() {
        this.x = 0;
        this.y = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public PolarPosition toPolarPosition() {
        double radius = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        double angle = Math.toDegrees(Math.atan(y/x));
        return new PolarPosition(radius, angle);
    }
}
