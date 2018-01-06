package springbootexample.dto.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "documents")
@Data
public class TextDocument {
    @Id
    private String id;
    private String name;
    private Integer version;
    private String text;
}
