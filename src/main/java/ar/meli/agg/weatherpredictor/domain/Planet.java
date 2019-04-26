package ar.meli.agg.weatherpredictor.domain;

public class Planet extends Element implements Movable {

    private String name;

    private Speed speed;

    public Planet(String name, PolarPosition polarPosition, Speed speed) {
        super(polarPosition);
        this.name =  name;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public Speed getSpeed() {
        return speed;
    }

    @Override
    public void move() {
        this.polarPosition.moveAngle(speed.getValue());
    }
}
