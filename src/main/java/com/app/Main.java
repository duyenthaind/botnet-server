package com.app;

import com.app.server.TcpServer;

/**
 * @author duyenthai
 */
public class Main {
    public static void main(String[] args) throws Exception {
        TcpServer server = new TcpServer();
        server.run();

        System.out.println("Tcp server is listening on port 8999...");
    }
}