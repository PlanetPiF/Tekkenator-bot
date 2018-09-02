package com.planetpif.tekkenator.bot.service;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface Dispatcher {
	
	public int analyze(MessageReceivedEvent messageReceivedEvent);
	
	public void dispatch(int commandType, MessageReceivedEvent messageReceivedEvent);
	
	public void init();
	
}
