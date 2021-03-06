package springbootexample.dao.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;
import springbootexample.dto.mongo.DocumentCounter;
import springbootexample.dto.mongo.TextDocument;

@Component
@Profile("mongo-aggregation")
public class MongoAggregationDao {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public MongoAggregationDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public TextDocument getLastDocumentByName(final String name) {
        TypedAggregation<TextDocument> request = lastDocumentByNameRequest(name);
        return mongoTemplate.aggregateStream(request, TextDocument.class).next();
    }

    public DocumentCounter getDocumentsNumberByName(final String name) {
        TypedAggregation<TextDocument> request = countDocumentsByName(name);
        return mongoTemplate.aggregateStream(request, DocumentCounter.class).next();
    }

    private TypedAggregation<TextDocument> lastDocumentByNameRequest(final String name) {
        MatchOperation match = Aggregation.match(Criteria.where("name").is(name));
        SortOperation sort = Aggregation.sort(Sort.Direction.DESC, "version");
        GroupOperation group = Aggregation.group("name")
                .first("id").as("id")
                .first("name").as("name")
                .max("version").as("version")
                .first("text").as("text");
        ProjectionOperation projection = Aggregation.project("name", "version", "text")
                .andExpression("id").as("_id");
        return Aggregation.newAggregation(TextDocument.class, match, sort, group, projection);
    }

    private TypedAggregation<TextDocument> countDocumentsByName(final String name) {
        MatchOperation match = Aggregation.match(Criteria.where("name").is(name));
        GroupOperation group = Aggregation.group("name").count().as("total");
        return Aggregation.newAggregation(TextDocument.class, match, group);
    }
}
