package springbootexample.service.rest.dbean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.client.RestTemplate;
import springbootexample.model.ClientProperties;

public class ClientBuilder implements FactoryBean<Client> {

    private ClientProperties properties;
    private RestTemplate restTemplate;

    @Override
    public Client getObject() throws Exception {
        return new Client(properties.getName(), properties.getUrl(), restTemplate);
    }

    @Override
    public Class<?> getObjectType() {
        return Client.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public ClientProperties getProperties() {
        return properties;
    }

    public void setProperties(ClientProperties properties) {
        this.properties = properties;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
}
