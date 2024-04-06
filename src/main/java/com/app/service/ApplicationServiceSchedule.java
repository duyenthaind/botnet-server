package com.app.service;

import com.app.worker.CheckClientConnectionWorker;
import com.app.worker.ScheduleWorker;

import java.util.concurrent.TimeUnit;

/**
 * @author duyenthai
 */
public class ApplicationServiceSchedule implements ApplicationService {
    @Override
    public void process() {
        ScheduleWorker.getInstance().schedule(new CheckClientConnectionWorker(), 30, 30, TimeUnit.SECONDS);
    }
}
