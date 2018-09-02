package com.planetpif.tekkenator.bot.service;

import java.util.Collection;

import com.planetpif.tekkenator.model.BotCommand;

public interface BotCommandService {

	public void addNewCommand(BotCommand botCommand);

	public void addNewCommands(Collection<BotCommand> botCommands);

	public boolean generateMockCommands();
}
