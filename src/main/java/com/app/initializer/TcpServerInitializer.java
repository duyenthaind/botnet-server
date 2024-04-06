package com.app.initializer;

import com.app.codec.Decoder;
import com.app.codec.Encoder;
import com.app.handler.TcpServerHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

/**
 * @author duyenthai
 */
public class TcpServerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline().addLast(new Decoder());
        channel.pipeline().addLast(new Encoder());
        channel.pipeline().addLast(new TcpServerHandler());
    }
}
