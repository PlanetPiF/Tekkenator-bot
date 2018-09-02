package com.planetpif.tekkenator.bot;

public interface DiscordJdaBot {
    void sendMessage(String channel, String message);
    
    /**
     * Builds/ creates/ starts the discord bot.
     * @param token - from discord app website
     * @return
     */
    public boolean init(String token);
}
