package springbootexample.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.integration.util.DynamicPeriodicTrigger;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import springbootexample.logic.scheduling.DynamicScheduledTask;
import springbootexample.logic.scheduling.ScheduledTask;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableScheduling
@Profile("scheduling")
public class SchedulingConfiguration implements SchedulingConfigurer {

    @Value("${scheduling.period-in-seconds}")
    private int period;

    @Value("${scheduling.range}")
    private int range;

    @Bean
    public ScheduledTask dynamicScheduledTask() {
        DynamicPeriodicTrigger trigger = new DynamicPeriodicTrigger(period, TimeUnit.SECONDS);
        return new DynamicScheduledTask(trigger, period, range);
    }

    @Override
    public void configureTasks(final ScheduledTaskRegistrar taskRegistrar) {
        ScheduledTask task = dynamicScheduledTask();
        taskRegistrar.addTriggerTask(task::process, ((DynamicScheduledTask) task).getTrigger());
    }
}
