package com.app.processor;

import com.app.codec.AppPacket;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author duyenthai
 */
public interface ChannelInboundProcessor {
    void process(ChannelHandlerContext ctx, AppPacket packet);
}
