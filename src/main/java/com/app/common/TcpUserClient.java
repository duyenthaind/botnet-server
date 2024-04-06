package com.app.common;

import com.app.codec.AppPacket;
import io.netty.channel.Channel;

/**
 * @author duyenthai
 */
public class TcpUserClient {
    private String clientId;
    private Channel channel;
    private Long lastActiveTime;

    public String getClientId() {
        return clientId;
    }

    public Channel getChannel() {
        return channel;
    }

    public Long getLastActiveTime() {
        return lastActiveTime;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void setLastActiveTime(Long lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public void sendPacket(AppPacket packet) {
        channel.writeAndFlush(packet);
    }
}
