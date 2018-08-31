package com.planetpif.tekkenator.bot.utils;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface CommandDispatcher {
	
	public int analyze(MessageReceivedEvent event);
	
	public void dispatch(int commandType, MessageReceivedEvent event);
	
	public void init();
	
}
