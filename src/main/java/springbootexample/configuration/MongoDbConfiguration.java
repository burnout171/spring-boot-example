package springbootexample.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mongo-aggregation")
public class MongoDbConfiguration {
}
