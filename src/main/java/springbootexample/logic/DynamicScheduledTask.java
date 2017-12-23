package springbootexample.logic;

import org.springframework.integration.util.DynamicPeriodicTrigger;

import java.util.Random;

public class DynamicScheduledTask implements ScheduledTask {

    private final DynamicPeriodicTrigger trigger;
    private final int period;
    private final int range;
    private final Random generator;

    public DynamicScheduledTask(final DynamicPeriodicTrigger trigger, int period, int range) {
        this.trigger = trigger;
        this.period = period;
        this.range = range;
        this.generator = new Random();
    }

    @Override
    public void process() {
        int next = generator.nextInt(range);
        if (next % 2 == 0) {
            System.out.println(String.format("Set trigger to %d seconds", period));
            trigger.setPeriod(period);
        } else {
            System.out.println("Set trigger to 3 seconds");
            trigger.setPeriod(3);
        }
    }

    public DynamicPeriodicTrigger getTrigger() {
        return trigger;
    }
}
