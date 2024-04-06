package com.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duyenthai
 */
public class ApplicationServiceImpl implements ApplicationService {

    private static final Logger LOGGER = LogManager.getLogger("ApplicationServiceImpl");

    private final List<ApplicationService> services = new ArrayList<>();

    public ApplicationServiceImpl() {
        services.add(new ApplicationServiceLogger());
        services.add(new ApplicationServiceConfig());
        services.add(new ApplicationServiceSchedule());
        services.add(new ApplicationServiceTcpServer());
    }

    @Override
    public void process() {
        for (ApplicationService service : services) {
            service.process();
            LOGGER.info("ApplicationServiceImpl.process() service {} started", service.getClass().getSimpleName());
        }
    }
}
