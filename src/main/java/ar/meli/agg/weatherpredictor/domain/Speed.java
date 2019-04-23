package ar.meli.agg.weatherpredictor.domain;

public class Speed {

    private int angle;

    private int time;

    public Speed(int angle) {
        this(angle,1);
    }

    private Speed(int angle, int time){
        this.angle = angle;
        this.time = time;
    }

    int getAngle() {
        return angle;
    }

    int getTime() {
        return time;
    }

    long getValue() {
        return angle/time;
    }
}
