package ar.meli.agg.weatherpredictor.repository;

import ar.meli.agg.weatherpredictor.domain.WeatherPrediction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WPRepository extends MongoRepository<WeatherPrediction, Integer> {

}
