package com.planetpif.tekkenator.bot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.dao.FighterRepository;
import com.planetpif.tekkenator.dao.MoveRepository;
import com.planetpif.tekkenator.model.Fighter;
import com.planetpif.tekkenator.model.Move;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

@Service
public class CommandDispatcherImpl implements CommandDispatcher {


	@Autowired
	private FighterRepository fighterRepository;

	@Autowired
	private MoveRepository moveRepository;
	
	//TODO Use enum ?
	private static final int UNKNOWN = -1;
	private static final int HELP = 0;
	private static final int MOVES = 1;
	private static final int PING = 2;
	private static final int HI = 3;

	@Override
	public int analyze(MessageReceivedEvent event) {

		int commandType = -1;
		String messageRaw = event.getMessage().getContentRaw();

		switch (messageRaw) {
		case "!help":
			commandType = HELP;
			break;
		case "!moves":
			commandType = MOVES;
			break;
		case "!ping":
			commandType = PING;
			break;
		case "!hi":
			commandType = HI;
			break;
		default:
			commandType = UNKNOWN;
			break;
		}

		return commandType;
	}

	@Override
	public void dispatch(int commandType, MessageReceivedEvent event) {
		// TODO Auto-generated method stub

		String dispatchMessage = "";

		switch (commandType) {
		case HELP:
			dispatchMessage = "Imagine there's a list of available commands here :)  (work in progress)";
			break;
		case MOVES:
			dispatchMessage = getMoves(null);
			break;
		case PING:
			dispatchMessage = "PONG!";
			break;
		case HI:
			dispatchMessage = "Hello! :)";
			break;
		}

		// Last but not least - send message at the end of function
		event.getChannel().sendMessage(dispatchMessage).queue();

	}

	public String getMoves(Fighter fighter) {
		String dispatchMessage = "";
		
		if(fighter == null) {
			dispatchMessage += " Fighter not specified \n";
		}
		
		Iterable<Move> movesList = moveRepository.findAll();
		System.out.println("Total Moves: " + moveRepository.count());
		dispatchMessage += "Total Moves Found: " + moveRepository.count() + "\n";
		for (Move move : movesList) {
			dispatchMessage += (move.getName() + " belongs to " + move.getFighter().getName() + ". \n");
		}
		return dispatchMessage;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		// not needed?

	}

}
