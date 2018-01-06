package springbootexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springbootexample.dao.mongo.MongoAggregationDao;
import springbootexample.dto.mongo.DocumentCounter;
import springbootexample.dto.mongo.TextDocument;

@RestController
@Profile("mongo-aggregation")
public class MongoAggregationRestController {

    private final MongoAggregationDao dao;

    @Autowired
    public MongoAggregationRestController(final MongoAggregationDao dao) {
        this.dao = dao;
    }

    @RequestMapping("getLastDocument")
    public TextDocument getLastDocumentByName(@RequestParam(value = "name") final String name) {
        return dao.getLastDocumentByName(name);
    }

    @RequestMapping("getDocumentsNumber")
    public DocumentCounter getDocumentsCountByName(@RequestParam(value = "name") final String name) {
        return dao.getDocumentsNumberByName(name);
    }
}
