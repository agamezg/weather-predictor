package ar.meli.agg.weatherpredictor.domain;

public class Speed {

    private double angle;

    private int time;

    public Speed(int angle) {
        this(angle,1);
    }

    public Speed(int angle, int time){
        this.angle = angle;
        this.time = time;
    }

    double getAngle() {
        return angle;
    }

    int getTime() {
        return time;
    }

    double getValue() {
        return angle/time;
    }
}
