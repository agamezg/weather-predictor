package ar.meli.agg.weatherpredictor.service;

import ar.meli.agg.weatherpredictor.domain.SolarSystem;
import ar.meli.agg.weatherpredictor.domain.WeatherPrediction;
import ar.meli.agg.weatherpredictor.repository.WPRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class WPService implements CommandLineRunner {

    @Value("${simulate.years}")
    private int years;

    private final WPRepository wpRepository;

    private final SolarSystem solarSystem;

    @Autowired
    public WPService(WPRepository wpRepository) {
        this.wpRepository = wpRepository;
        this.solarSystem = SolarSystem.getInstance();
    }

    public WeatherPrediction find(Integer day) {
        WeatherPrediction weatherPrediction = null;
        if (wpRepository.findById(day).isPresent())
            weatherPrediction = wpRepository.findById(day).get();
        return weatherPrediction;
    }

    // asumo que un año tendrá una duración de 72 días
    // que es el período de los vulcanos
    private void simulate(int years){
        int period = solarSystem.getVulcanoPeriod();
        int simulationLimit = (years * period);
        WeatherPrediction wp;
        for (int i = 0; i < simulationLimit; i++){
            wp = solarSystem.prediction();
            wpRepository.save(wp);
            solarSystem.nextDay();
        }
    }

    @Override
    public void run(String... args) throws Exception {
        simulate(years);
    }
}
