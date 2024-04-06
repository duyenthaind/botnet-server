package com.app.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author duyenthai
 */
public class Decoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 4) {
            return;
        }

        in.markReaderIndex();

        long packetId = in.readLong();
        int packetLength = in.readInt();
        short type = in.readShort();
        byte[] data = new byte[packetLength];


        AppPacket packet = new AppPacket();
        packet.setLength(packetLength);

        if (in.readableBytes() < packetLength) {
            in.resetReaderIndex();
            return;
        }

        in.readBytes(data);
        packet.setData(data);

        packet.setId(packetId);
        packet.setType(type);
        out.add(packet);

    }
}
