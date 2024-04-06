package com.app.server;

import com.app.common.AppConfig;
import com.app.common.SingletonHolder;
import com.app.common.TcpServerConfig;
import com.app.initializer.TcpServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author duyenthai
 */
public class TcpServer {

    private static final Logger LOGGER = LogManager.getLogger("TcpServer");

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;

    public void run() throws Exception {

        AppConfig appConfig = SingletonHolder.getInstance().getConfig();
        if (appConfig == null) {
            LOGGER.error("TcpServer.run() Config is not loaded, exit now");
            System.exit(1);
        }
        TcpServerConfig config = appConfig.getTcpServer();

        bossGroup = new NioEventLoopGroup(1);
        workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new TcpServerInitializer());

        bootstrap.bind(config.getListenAddress(), config.getPort()).sync();

        LOGGER.info("TcpServer started on host {}, port {}", config.getListenAddress(), config.getPort());
    }

    public void stop() {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }
}
