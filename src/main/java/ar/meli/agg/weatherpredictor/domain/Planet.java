package ar.meli.agg.weatherpredictor.domain;

public class Planet extends Element implements Translation{

    private Speed speed;

    private int period;

    Planet(PolarPosition polarPosition, Speed speed) {
        super(polarPosition);
        this.speed = speed;
        this.period = calculatePeriod();
    }

    private int calculatePeriod() {
        return Math.abs(360/(speed.getAngle()/speed.getTime()));
    }

    public Speed getSpeed() {
        return speed;
    }

    public int getPeriod() {
        return period;
    }


    @Override
    public void move() {
        this.polarPosition.increaseAngle(speed.getAngle());
    }
}
