package com.app.command;

import com.app.common.CommandTypeDefs;

/**
 * @author duyenthai
 */
public class BotnetCommand extends BaseCommand {

    private String url;
    private long time;

    protected BotnetCommand() {
        super(CommandTypeDefs.ON_ACTION);
    }

    @Override
    public void writeData() {
        data.addProperty("url", url);
        data.addProperty("time", time);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
