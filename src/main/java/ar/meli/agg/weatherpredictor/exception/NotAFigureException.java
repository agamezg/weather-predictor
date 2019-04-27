package ar.meli.agg.weatherpredictor.exception;

public class NotAFigureException extends Exception {

    @Override
    public String getMessage() {
        return "Is not a Figure";
    }
}
