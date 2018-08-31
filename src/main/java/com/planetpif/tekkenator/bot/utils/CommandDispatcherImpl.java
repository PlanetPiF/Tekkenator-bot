package com.planetpif.tekkenator.bot.utils;

import java.awt.Color;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.dao.FighterRepository;
import com.planetpif.tekkenator.dao.MoveRepository;
import com.planetpif.tekkenator.model.Fighter;
import com.planetpif.tekkenator.model.Move;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

@Service
public class CommandDispatcherImpl implements CommandDispatcher {

	@Autowired
	private FighterRepository fighterRepository;

	@Autowired
	private MoveRepository moveRepository;

	// TODO Use enum ?
	private static final int UNKNOWN = -1;
	private static final int HELP = 0;
	private static final int MOVES = 1;
	private static final int PING = 2;
	private static final int HI = 3;
	private static final int LOVE = 4;
	private static final int ROLL = 5;

	// TODO do I really need this ?
	private MessageReceivedEvent event;

	@Override
	public int analyze(MessageReceivedEvent messageReceivedEvent) {

		this.setEvent(messageReceivedEvent);

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
		case "!love":
			commandType = LOVE;
			break;
		case "!roll":
			commandType = ROLL;
			break;
		default:
			commandType = UNKNOWN;
			break;
		}

		return commandType;
	}

	@Override
	public void dispatch(int commandType, MessageReceivedEvent messageReceivedEvent) {

		this.setEvent(messageReceivedEvent);

		String textMessage = "";
		MessageEmbed emb = null;
		textMessage += event.getAuthor().getAsMention() + "\n";
		switch (commandType) {
		case HELP:
			emb = EmbedTest();
			break;
		case MOVES:
			textMessage = getMoves(null);
			break;
		case PING:
			textMessage = "PONG!";
			break;
		case HI:
			textMessage = "Hello! :)";
			break;
		case ROLL:
			textMessage = Roll(null);
			break;
		default:
			break;
		}
		
		

		System.out.println("How to @MENTION people: ");
		System.out.println(event.getAuthor().getAsMention());

		textMessage += "\n" + event.getAuthor().getAsMention() + "\n";

		// Last but not least - send message at the end of function
		if(emb != null) {
		//	System.out.println("emb looks like: ");
		//	System.out.println(emb.toJSONObject().toString());   // can i convert backwards from JSON ? 
			event.getChannel().sendMessage(emb).queue();
		} else {
			event.getChannel().sendMessage(textMessage).queue();
		}
		

	}
	
	
	public MessageEmbed EmbedTest() {
		
		// Create the EmbedBuilder instance
		EmbedBuilder eb = new EmbedBuilder();

		/*
		    Set the title:
		    1. Arg: title as string
		    2. Arg: URL as string or could also be null
		 */
		eb.setTitle("Title", "https://google.com");

		/*
		    Set the color
		 */
		eb.setColor(Color.red);
		eb.setColor(new Color(0xF40C0C));
		eb.setColor(new Color(255, 0, 54));

		/*
		    Set the text of the Embed:
		    Arg: text as string
		 */
		eb.setDescription("Description");

		/*
		    Add fields to embed:
		    1. Arg: title as string
		    2. Arg: text as string
		    3. Arg: inline mode true / false
		 */
		eb.addField("Title of field", "test of field", false);

		/*
		    Add spacer like field
		    Arg: inline mode true / false
		 */
		eb.addBlankField(false);

		/*
		    Add embed author:
		    1. Arg: name as string
		    2. Arg: url as string (can be null)
		    3. Arg: icon url as string (can be null)
		 */
		eb.setAuthor("name", null, "https://github.com/zekroTJA/DiscordBot/blob/master/.websrc/zekroBot_Logo_-_round_small.png");

		/*
		    Set footer:
		    1. Arg: text as string
		    2. icon url as string (can be null)
		 */
		eb.setFooter("Text", "https://github.com/zekroTJA/DiscordBot/blob/master/.websrc/zekroBot_Logo_-_round_small.png");

		/*
		    Set image:
		    Arg: image url as string
		 */
		eb.setImage("https://github.com/zekroTJA/DiscordBot/blob/master/.websrc/logo%20-%20title.png");

		/*
		    Set thumbnail image:
		    Arg: image url as string
		 */
		eb.setThumbnail("https://github.com/zekroTJA/DiscordBot/blob/master/.websrc/logo%20-%20title.png");
		
		
		return eb.build();
	}

	public String Roll(Integer maxRoll) {

		int max = 100;
		if (maxRoll != null) {
			max = maxRoll;
		}

		Random rand = new Random();
		int roll = rand.nextInt(max) + 1; // This results in 1 - max (instead of 0 - max)
		return "Your rolled (0 - " + max + "):  " + roll;

	}

	public String getMoves(Fighter fighter) {
		String dispatchMessage = "";

		if (fighter == null) {
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

	public MessageReceivedEvent getEvent() {
		return event;
	}

	public void setEvent(MessageReceivedEvent event) {
		this.event = event;
	}

}
