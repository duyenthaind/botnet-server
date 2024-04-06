package com.app.common;

/**
 * @author duyenthai
 */
public class TcpServerConfig {
    private String listenAddress;
    private int port;

    public String getListenAddress() {
        return listenAddress;
    }

    public int getPort() {
        return port;
    }

    public void setListenAddress(String listenAddress) {
        this.listenAddress = listenAddress;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
