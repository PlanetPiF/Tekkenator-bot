package com.planetpif.tekkenator.bot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.bot.utils.CommandDispatcher;
import com.planetpif.tekkenator.dao.FighterRepository;
import com.planetpif.tekkenator.dao.MoveRepository;
import com.planetpif.tekkenator.model.Move;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

@Service
public class MyBotListener extends ListenerAdapter implements BotListenterInterface {

	@Autowired
	DataSource dataSource;

	@Autowired
	private FighterRepository fighterRepository;

	@Autowired
	private MoveRepository moveRepository;
	
	@Autowired
	private CommandDispatcher commandDispatcher;

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot())
			return;

		
		int eventType = commandDispatcher.analyze(event);
		
		// We don't handle non ! commands
		if(eventType == -1) {
			return;
		}

		commandDispatcher.dispatch(eventType, event);

	}

	@Override
	public void onReady(ReadyEvent event) {
		System.out.println("Tekkenator Online!");
	}
}
