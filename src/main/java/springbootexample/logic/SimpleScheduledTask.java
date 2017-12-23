package springbootexample.logic;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SimpleScheduledTask implements ScheduledTask {
    @Scheduled(fixedDelay = 5000)
    @Override
    public void process() {
        System.out.println("Process...");
    }
}
