package ar.meli.agg.weatherpredictor.config;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "ar.meli.agg.weatherpredictor.repository")
public class MongoConfig extends AbstractMongoConfiguration {


    @Override
    public MongoClient mongoClient() {
        return new MongoClient("127.0.0.1", 27017);
    }

    @Override
    protected String getDatabaseName() {
        return "weather_predictor";
    }
}
