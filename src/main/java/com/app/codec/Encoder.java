package com.app.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author duyenthai
 */
public class Encoder extends MessageToByteEncoder<AppPacket> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, AppPacket appPacket, ByteBuf byteBuf) throws Exception {
        byteBuf.writeLong(appPacket.getId());
        byteBuf.writeInt(appPacket.getLength());
        byteBuf.writeShort(appPacket.getType());
        byteBuf.writeBytes(appPacket.getData());
    }
}
