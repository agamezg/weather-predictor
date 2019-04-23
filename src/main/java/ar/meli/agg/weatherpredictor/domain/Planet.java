package ar.meli.agg.weatherpredictor.domain;

public class Planet extends Element implements Translation{

    private String name;

    private Speed speed;

    private int period;

    public Planet(String name, PolarPosition polarPosition, Speed speed) {
        super(polarPosition);
        this.name =  name;
        this.speed = speed;
        this.period = calculatePeriod();
    }

    private int calculatePeriod() {
        return Math.abs(360/(speed.getAngle()/speed.getTime()));
    }

    public String getName() {
        return name;
    }

    public Speed getSpeed() {
        return speed;
    }

    public int getPeriod() {
        return period;
    }


    @Override
    public void move() {
        this.polarPosition.moveAngle(speed.getValue());
    }
}
