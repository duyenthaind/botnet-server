package com.app.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.File;

/**
 * @author duyenthai
 */
public class ApplicationServiceLogger implements ApplicationService {

    private static final Logger LOGGER = LogManager.getLogger("ApplicationServiceLogger");
    private final String configFolder = System.getProperty("user.dir") + File.separator + "config";

    @Override
    public void process() {
        String log4jConfig = configFolder + File.separator + "log4j2.xml";
        Configurator.initialize("Main", log4jConfig);

        LOGGER.info("ApplicationServiceLogger.process() log4j2 initialized");
    }
}
