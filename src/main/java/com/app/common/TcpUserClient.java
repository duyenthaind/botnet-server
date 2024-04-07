package com.app.common;

import com.app.codec.AppPacket;
import io.netty.channel.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author duyenthai
 */
public class TcpUserClient {

    private static final Logger LOGGER = LogManager.getLogger("TcpUserClient");

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
        try {
            channel.writeAndFlush(packet);
        } catch (Exception ex) {
            LOGGER.error("Try to send packet to clientId {} error ", this.clientId, ex);
        }
    }
}
