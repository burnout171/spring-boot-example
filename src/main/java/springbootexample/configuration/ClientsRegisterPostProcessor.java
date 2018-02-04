package springbootexample.configuration;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import springbootexample.model.ClientProperties;
import springbootexample.service.rest.dbean.ClientBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@Profile("dynamic-bean-creation")
public class ClientsRegisterPostProcessor implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    private final Map<String, ClientProperties> clients = new HashMap<>();

    @Override
    public void postProcessBeanDefinitionRegistry(final BeanDefinitionRegistry registry) {
        for(Map.Entry<String, ClientProperties> entry: clients.entrySet()) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(ClientBuilder.class)
                    .addPropertyValue("properties", entry.getValue())
                    .addPropertyReference("restTemplate", "restTemplate");
            registry.registerBeanDefinition(entry.getKey(), builder.getBeanDefinition());
        }
    }

    @Override
    public void postProcessBeanFactory(final ConfigurableListableBeanFactory beanFactory) {
    }

    @Override
    public void setEnvironment(final Environment environment) {
        String allClients = environment.getProperty("clients.all");
        if (Objects.nonNull(allClients)) {
            String[] clientNames = allClients.split(",");
            fillClientProperties(environment, clientNames);
        }
    }

    private void fillClientProperties(final Environment environment, final String[] clientNames) {
        for (String name : clientNames) {
            String trimmed = name.trim();
            ClientProperties properties = new ClientProperties()
                    .setName(trimmed)
                    .setUrl(environment.getProperty(String.format("clients.%s.url", trimmed)));
            clients.put(trimmed, properties);
        }
    }
}
