package com.planetpif.tekkenator.bot;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.bot.utils.CommandDispatcher;

import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

@Service
public class MyBotListener extends ListenerAdapter implements BotListenterInterface {

	static final Logger logger = Logger.getLogger(MyBotListener.class);

	@Autowired
	DataSource dataSource;

	@Autowired
	private CommandDispatcher commandDispatcher;

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot())
			return;

		int eventType = commandDispatcher.analyze(event);

		// We don't handle non ! commands
		if (eventType == -1) {
			return;
		}

		commandDispatcher.dispatch(eventType, event);
		logger.info("Message sent!");

	}

	@Override
	public void onReady(ReadyEvent event) {
		logger.info("Tekkenator Online!");
	}
}
