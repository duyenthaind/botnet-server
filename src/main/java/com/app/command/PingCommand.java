package com.app.command;

import com.app.common.CommandTypeDefs;

/**
 * @author duyenthai
 */
public class PingCommand extends BaseCommand {
    public PingCommand() {
        super(CommandTypeDefs.PING);
    }

    @Override
    public void writeData() {

    }
}
