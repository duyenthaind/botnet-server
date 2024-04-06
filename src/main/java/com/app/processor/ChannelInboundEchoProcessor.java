package com.app.processor;

import com.app.codec.AppPacket;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author duyenthai
 */
public class ChannelInboundEchoProcessor implements ChannelInboundProcessor {
    @Override
    public void process(ChannelHandlerContext ctx, AppPacket packet) {
        System.out.println("echoooooo");
        if (packet.getData() != null && packet.getData().length > 0) {
            String dataContent = new String(packet.getData());
            System.out.println("This packet is " + dataContent);
            if (dataContent.equals("test")) {
                AppPacket resPacket = new AppPacket();
                resPacket.setData("ok".getBytes());
                resPacket.setLength("ok".length());
                ctx.writeAndFlush(resPacket);
            }
        }
    }
}
