package com.app.command;

import com.app.codec.AppPacket;
import com.app.common.CommandTypeDefs;
import com.google.gson.JsonObject;

import java.nio.charset.StandardCharsets;

/**
 * @author duyenthai
 */
public abstract class BaseCommand {
    protected CommandTypeDefs type;
    protected JsonObject data = new JsonObject();

    public AppPacket writePacket() {
        writeData();

        AppPacket packet = new AppPacket();
        packet.setId(System.currentTimeMillis());
        packet.setType(type.getType());
        packet.setLength(data.toString().length());
        packet.setData(data.toString().getBytes(StandardCharsets.UTF_8));
        return packet;
    }

    protected BaseCommand(CommandTypeDefs type) {
        this.type = type;
    }

    public abstract void writeData();

    public CommandTypeDefs getType() {
        return type;
    }

    public JsonObject getData() {
        return data;
    }

    public void setType(CommandTypeDefs type) {
        this.type = type;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
