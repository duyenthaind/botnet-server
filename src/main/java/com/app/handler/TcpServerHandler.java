package com.app.handler;

import com.app.codec.AppPacket;
import com.app.processor.ChannelInboundEchoProcessor;
import com.app.processor.ChannelInboundProcessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duyenthai
 */
public class TcpServerHandler extends SimpleChannelInboundHandler<AppPacket> {

    private final List<ChannelInboundProcessor> processors = new ArrayList<>();

    public TcpServerHandler() {
        processors.add(new ChannelInboundEchoProcessor());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, AppPacket appPacket) throws Exception {
        try {
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
