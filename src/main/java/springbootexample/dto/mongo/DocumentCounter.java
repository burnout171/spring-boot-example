package springbootexample.dto.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class DocumentCounter {
    @JsonIgnore
    private String id;
    private Integer total;
}
