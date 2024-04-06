package com.app.codec;

/**
 * @author duyenthai
 */
public class AppPacket {
    private long id;
    private int length;
    private byte[] data;
    private short type;

    public long getId() {
        return id;
    }

    public int getLength() {
        return length;
    }

    public byte[] getData() {
        return data;
    }

    public short getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setType(short type) {
        this.type = type;
    }
}
