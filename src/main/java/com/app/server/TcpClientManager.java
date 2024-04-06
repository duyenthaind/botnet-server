package com.app.server;

import com.app.common.TcpUserClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author duyenthai
 */
public class TcpClientManager {

    private final Map<String, TcpUserClient> userClientMap = new HashMap<>();

    private TcpClientManager() {
    }

    public void addClient(String clientId, TcpUserClient client) {
        userClientMap.putIfAbsent(clientId, client);
    }

    public void removeClient(String clientId) {
        synchronized (userClientMap) {
            TcpUserClient tcpClient = userClientMap.remove(clientId);
            if (tcpClient != null) {
                tcpClient.getChannel().close();
            }
        }
    }

    public void removeClient(TcpUserClient client) {
        removeClient(client.getClientId());
    }

    public List<TcpUserClient> getAllClient() {
        return userClientMap.values().stream().toList();
    }

    public TcpUserClient getClient(String clientId) {
        return userClientMap.get(clientId);
    }

    public static TcpClientManager getInstance() {
        return Singleton.INSTANCE;
    }

    private static class Singleton {
        public static final TcpClientManager INSTANCE = new TcpClientManager();
    }
}
