package com.app.common;


import com.google.gson.Gson;

/**
 * @author duyenthai
 */
public class AppConfig {
    private TcpServerConfig tcpServer;

    public TcpServerConfig getTcpServer() {
        return tcpServer;
    }

    public void setTcpServer(TcpServerConfig tcpServer) {
        this.tcpServer = tcpServer;
    }

    public String printConfig() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
