package springbootexample.logic.dbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import springbootexample.service.rest.dbean.Client;

import java.util.Map;
import java.util.Objects;

@Component
@Profile("dynamic-bean-creation")
public class ClientRouter {

    private final Map<String, Client> clients;

    @Autowired
    public ClientRouter(Map<String, Client> clients) {
        this.clients = clients;
    }

    public void send(final String clientName) {
        Client client = clients.get(clientName);
        if (Objects.nonNull(client)) {
            client.send();
        }
    }
}
