package springbootexample.service.rest.dbean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class Client {

    private static final Logger log = LoggerFactory.getLogger(Client.class);

    private final String name;
    private final URI url;
    private final RestTemplate restTemplate;

    Client(final String name, final String url, final RestTemplate restTemplate) {
        this.name = name;
        this.url = URI.create(url);
        this.restTemplate = restTemplate;
    }

    public String send() {
        log.info(name + ".send");
        return restTemplate.getForEntity(url, String.class).getBody();
    }
}
