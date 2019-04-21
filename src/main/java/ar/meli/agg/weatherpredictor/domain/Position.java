package ar.meli.agg.weatherpredictor.domain;

public class Position {

    private long radius;

    private long angle;

    public Position(long radius, long angle) {
        this.radius = radius;
        this.angle = angle;
    }

    public Position() {
        this.radius = 0;
        this.angle = 0;
    }

    public long getRadius() {
        return radius;
    }

    public long getAngle() {
        return angle;
    }

    public void increaseAngle(long angle) {
        this.angle += angle;
    }
}
