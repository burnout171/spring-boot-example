package springbootexample.dao.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import springbootexample.dto.mongo.TextDocument;

@Component
@Profile("mongo-aggregation")
public class MongoDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public TextDocument getLastDocumentByName(final String name) {
        TypedAggregation<TextDocument> request = prepareAggregationRequest(name);
        return mongoTemplate.aggregateStream(request, TextDocument.class).next();
    }

    private TypedAggregation<TextDocument> prepareAggregationRequest(final String name) {
        MatchOperation match = Aggregation.match(Criteria.where("name").is(name));
        GroupOperation group = Aggregation.group("name")
                .last("id").as("id")
                .last("name").as("name")
                .max("version").as("version")
                .last("text").as("text");
        ProjectionOperation projection = Aggregation.project("name", "version", "text")
                .andExpression("_id").as("id");
        return Aggregation.newAggregation(TextDocument.class, match, group, projection);
    }
}
