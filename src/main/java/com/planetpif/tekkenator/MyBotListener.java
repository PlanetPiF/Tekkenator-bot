package com.planetpif.tekkenator;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if (event.getAuthor().isBot())
			return;

		Message message = event.getMessage();
		String chatCommand = message.getContentRaw();
		if (chatCommand.equals("!ping")) {
			MessageChannel channel = event.getChannel();
			channel.sendMessage("Pong!").queue();
		}

		if (chatCommand.equals("!hi")) {
			MessageChannel channel = event.getChannel();
			channel.sendMessage("Hello!").queue();
		}

		if (chatCommand.equals("!moves")) {
			String response = "";
			Iterable<Move> movesList = moveRepository.findAll();
			System.out.println("Total Moves: " + moveRepository.count());
			response += "Total Moves Found: " + moveRepository.count() + "\n";
			for (Move move : movesList) {
				response += (move.getName() + " belongs to " + move.getFighter().getName() + ". \n");
			}

			event.getChannel().sendMessage(response).queue();
		}

	}

	@Override
	public void onReady(ReadyEvent event) {
		System.out.println("Tekkenator Online!");
	}
}
