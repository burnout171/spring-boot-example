package springbootexample.logic.scheduling;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Profile("scheduling")
public class SimpleScheduledTask implements ScheduledTask {
    @Scheduled(fixedDelay = 5000)
    @Override
    public void process() {
        System.out.println("Process...");
    }
}
