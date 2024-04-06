package com.app.common;

/**
 * @author duyenthai
 */
public enum CommandTypeDefs {
    PING((short) 1),
    ON_ACTION((short) 2),
    ;
    private final short type;

    CommandTypeDefs(short type) {
        this.type = type;
    }

    public short getType() {
        return type;
    }
}
