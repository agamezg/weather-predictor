package ar.meli.agg.weatherpredictor.domain;


public class Element {

    Position position;

    Element(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
