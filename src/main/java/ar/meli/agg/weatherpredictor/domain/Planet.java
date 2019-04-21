package ar.meli.agg.weatherpredictor.domain;

class Planet extends Element implements Translation{

    private Speed speed;

    private int period;

    Planet(Position position, Speed speed) {
        super(position);
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
        this.position.increaseAngle(speed.getAngle());
    }
}
