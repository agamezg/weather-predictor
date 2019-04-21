package ar.meli.agg.weatherpredictor.domain;


public class Element {

    PolarPosition polarPosition;

    Element(PolarPosition polarPosition) {
        this.polarPosition = polarPosition;
    }

    public PolarPosition getPolarPosition() {
        return polarPosition;
    }

    public CartesianPosition getCartesianPosition(){return polarPosition.getCartesianPosition();}
}
