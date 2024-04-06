package com.app.service;

import com.app.server.TcpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author duyenthai
 */
public class ApplicationServiceTcpServer implements ApplicationService {

    private static final Logger LOGGER = LogManager.getLogger("ApplicationServiceTcpServer");

    private TcpServer server;

    @Override
    public void process() {
        server = new TcpServer();
        try {
            server.run();
        } catch (Exception ex) {
            LOGGER.error("Start tcp server error ", ex);
        }
    }
}
