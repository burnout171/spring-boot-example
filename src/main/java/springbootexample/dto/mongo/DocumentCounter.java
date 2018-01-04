package springbootexample.dto.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class DocumentCounter {

    @JsonIgnore
    private String id;
    private Integer total;

    public DocumentCounter() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
