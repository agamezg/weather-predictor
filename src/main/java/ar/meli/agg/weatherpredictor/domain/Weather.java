package ar.meli.agg.weatherpredictor.domain;

public enum Weather {
    DROUGHT("drought"),
    RAIN("rain"),
    HARD_RAIN("hard rain"),
    BEAUTIFUL_DAY("beautiful day"),
    CLOUDY("cloudy");

    private String weather;

    Weather(String weather){
        this.weather = weather;
    }

    @Override
    public String toString() {
        if(weather != null)
            return weather;
        else
            return "";
    }
}
