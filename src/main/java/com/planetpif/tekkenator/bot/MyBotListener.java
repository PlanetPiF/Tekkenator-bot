package com.planetpif.tekkenator.bot;

import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.bot.service.Analyzer;
import com.planetpif.tekkenator.bot.service.Dispatcher;

import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

@Service
public class MyBotListener extends ListenerAdapter implements BotListenterInterface {

	static final Logger log = Logger.getLogger(MyBotListener.class);

	@Autowired
	DataSource dataSource;
	
	@Autowired
	private Analyzer analyzer;

	@Autowired
	private Dispatcher commandDispatcher;

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot())
			return;

		int eventType = commandDispatcher.analyze(event);

		log.info("Guild / Server name: "  + event.getGuild().getName()); //TODO this only works for text channels, not PMs
		
		List<Emote> emotes = event.getGuild().getEmotesByName("d_", true);
		if(emotes != null) {
			log.info("Received emotes from guild! - count is " + emotes.size());
			Emote e = emotes.get(0);
			log.info("Emote as mention: " + e.getAsMention());
		}
		// We don't handle non ! commands
		if (eventType == -1) {
			return;
		}

		commandDispatcher.dispatch(eventType, event);
		log.info("Message sent!");

	}

	@Override
	public void onReady(ReadyEvent event) {
		log.info("Tekkenator Online!");
	}
}
