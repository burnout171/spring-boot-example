package springbootexample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springbootexample.dao.mongo.MongoDao;
import springbootexample.dto.mongo.TextDocument;

@RestController
@Profile("mongo-aggregation")
public class MongoRestController {

    private final MongoDao dao;

    @Autowired
    public MongoRestController(final MongoDao dao) {
        this.dao = dao;
    }

    @RequestMapping("getLastDocuments")
    public TextDocument getLastDocumentByName(@RequestParam(value = "name") final String name) {
        return dao.getLastDocumentByName(name);
    }

}
