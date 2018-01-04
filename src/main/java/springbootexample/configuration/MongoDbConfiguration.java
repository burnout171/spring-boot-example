package springbootexample.configuration;

import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mongo-aggregation")
@Import(value = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class MongoDbConfiguration {
}
