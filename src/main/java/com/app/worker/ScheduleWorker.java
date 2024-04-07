package com.app.worker;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author duyenthai
 */
public class ScheduleWorker {

    private final ScheduledExecutorService scheduleExecutor = Executors.newScheduledThreadPool(1);

    private ScheduleWorker() {
    }

    public void schedule(Runnable r, long initialDelay, long interval, TimeUnit timeUnit) {
        scheduleExecutor.scheduleWithFixedDelay(r, initialDelay, interval, timeUnit);
    }

    public static ScheduleWorker getInstance() {
        return Singleton.instance;
    }

    private static class Singleton {
        public static final ScheduleWorker instance = new ScheduleWorker();
    }
}
