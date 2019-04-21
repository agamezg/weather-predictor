package ar.meli.agg.weatherpredictor.repository;

import ar.meli.agg.weatherpredictor.domain.WeatherPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WPRepository extends JpaRepository<WeatherPrediction, Integer> {

}
