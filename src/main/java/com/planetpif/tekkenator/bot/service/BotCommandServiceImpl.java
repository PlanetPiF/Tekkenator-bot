package com.planetpif.tekkenator.bot.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.dao.BotCommandRepository;
import com.planetpif.tekkenator.model.BotCommand;
import com.planetpif.tekkenator.model.CommandType;

@Service
public class BotCommandServiceImpl implements BotCommandService {

	static final Logger logger = Logger.getLogger(BotCommandServiceImpl.class);

	@Autowired
	private BotCommandRepository botCommandRepository;

	@Override
	public void addNewCommand(BotCommand botCommand) {
		botCommandRepository.save(botCommand);
		logger.info("Added new command: "+ botCommand.getName());
	}

	@Override
	public boolean generateMockCommands() {

		//  add !help command
		BotCommand help = new BotCommand();
		help.setName("!help");
		help.setAliases(".help");
		help.setType(CommandType.HELP.getCode());
		help.setDescription("Displays all available commands.");
		this.addNewCommand(help);
		
		// add !legend command
		BotCommand legend = new BotCommand();
		legend.setName("!legend");
		legend.setAliases("!notation,!info");
		legend.setType(CommandType.LEGEND.getCode());
		legend.setDescription("Displays some of the notation / abbreviations used.");
		this.addNewCommand(legend);
		
		// add !format command
		BotCommand format = new BotCommand();
		format.setName("!format");
		format.setAliases("!f");
		format.setType(CommandType.FORMATREQUEST.getCode());
		format.setDescription("Converts a move message (e.g.  b+4) into visual elements.");
		this.addNewCommand(format);
		
		// add ![character] [move]
		

		return true;
	}

	@Override
	public void addNewCommands(Collection<BotCommand> botCommands) {
		botCommandRepository.saveAll(botCommands);
	}

}
