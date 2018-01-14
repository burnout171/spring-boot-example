package springbootexample.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientProperties {
    private String name;
    private String url;
}
