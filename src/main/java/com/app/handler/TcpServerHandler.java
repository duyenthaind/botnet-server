package com.app.handler;

import com.app.codec.AppPacket;
import com.app.common.TcpUserClient;
import com.app.processor.ChannelInboundEchoProcessor;
import com.app.processor.ChannelInboundProcessor;
import com.app.server.TcpClientManager;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author duyenthai
 */
public class TcpServerHandler extends SimpleChannelInboundHandler<AppPacket> {
    private final AttributeKey<TcpUserClient> CHANNEL_ATTACHMENT_ATTR = AttributeKey.valueOf("CHANNEL_ATTACHMENT_ATTR");

    private final List<ChannelInboundProcessor> processors = new ArrayList<>();

    public TcpServerHandler() {
        processors.add(new ChannelInboundEchoProcessor());
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        if (!ctx.channel().isActive()) {
            return;
        }

        TcpUserClient client = new TcpUserClient();
        client.setClientId(UUID.randomUUID().toString());
        client.setChannel(ctx.channel());
        client.setLastActiveTime(System.currentTimeMillis());
        ctx.channel().attr(CHANNEL_ATTACHMENT_ATTR).set(client);

        TcpClientManager.getInstance().addClient(client.getClientId(), client);

        super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        TcpUserClient client = ctx.channel().attr(CHANNEL_ATTACHMENT_ATTR).get();
        if (client != null) {
            TcpClientManager.getInstance().removeClient(client);
        }

        super.channelInactive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, AppPacket appPacket) throws Exception {
        try {
            TcpUserClient client = channelHandlerContext.channel().attr(CHANNEL_ATTACHMENT_ATTR).get();

            if (client != null) {
                client.setLastActiveTime(System.currentTimeMillis());
            }

            for (ChannelInboundProcessor processor : processors) {
                processor.process(channelHandlerContext, appPacket);
            }
        } finally {
            ReferenceCountUtil.release(appPacket);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
