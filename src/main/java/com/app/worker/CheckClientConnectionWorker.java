package com.app.worker;

import com.app.common.TcpUserClient;
import com.app.server.TcpClientManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author duyenthai
 */
public class CheckClientConnectionWorker implements Runnable {

    private static final Logger LOGGER = LogManager.getLogger("CheckClientConnectionWorker");

    @Override
    public void run() {
        List<TcpUserClient> tcpUserClients = TcpClientManager.getInstance().getAllClient();
        LOGGER.info("CheckClientConnectionWorker.run() schedule, numberClientConnected {}", tcpUserClients.size());
        for (TcpUserClient client : tcpUserClients) {
            long current = System.currentTimeMillis();
            if (current - client.getLastActiveTime() > TimeUnit.SECONDS.toMillis(30) * 3) {
                TcpClientManager.getInstance().removeClient(client);
                LOGGER.info("CheckClientConnectionWorker.run() remove client {}", client.getClientId());
            }
        }
    }
}
