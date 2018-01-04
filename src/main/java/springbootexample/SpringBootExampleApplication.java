package springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "springbootexample")
public class SpringBootExampleApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootExampleApplication.class, args);
	}
}
