package com.planetpif.tekkenator.bot;

public interface DiscordBot {
    void sendMessage(String guild, String channel, String message);
}
