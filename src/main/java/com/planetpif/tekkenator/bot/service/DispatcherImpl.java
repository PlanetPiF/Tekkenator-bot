package com.planetpif.tekkenator.bot.service;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.bot.utils.MoveTranslator;
import com.planetpif.tekkenator.dao.repository.FighterRepository;
import com.planetpif.tekkenator.dao.repository.MoveRepository;
import com.planetpif.tekkenator.model.Fighter;
import com.planetpif.tekkenator.model.Move;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Emote;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

@Service
public class DispatcherImpl implements Dispatcher {

	static final Logger log = Logger.getLogger(DispatcherImpl.class);
	
	@Autowired
	private FighterRepository fighterRepository;

	@Autowired
	private MoveRepository moveRepository;
	
	@Autowired
	private MoveTranslator moveTranslator;
	

	//private JDA jda;

	// TODO Use enum ?
	private static final int UNKNOWN = -1;
	private static final int HELP = 0;
	private static final int MOVES = 1;
	private static final int PING = 2;
	private static final int HI = 3;
	private static final int LOVE = 4;
	private static final int ROLL = 5;
	private static final int EWGF = 6;
	private static final int GUESS = 7;
	
	private static final String SAFE_ICON = "https://cdn1.iconfinder.com/data/icons/color-bold-style/21/30-512.png";
	private static final String UNSAFE_ICON = "https://st.depositphotos.com/1637332/2711/v/950/depositphotos_27116087-stock-illustration-bouton-internet-attention-exclamation-danger.jpg";
	
	@Value("${property.title}")
	private String propertyTitle;
	
	@Value("${onBlock.title}")
	private String onBlockTitle;
	
	@Value("${onHit.title}")
	private String onHitTitle;
	
	@Value("${onCounterHit.title}")
	private String onCounterHitTitle;
	
	@Value("${damage.title}")
	private String damageTitle;
	
	@Value("${startup.title}")
	private String startupTitle;
	
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
		case "!ewgf":
			commandType = EWGF;
			break;
		case "!g":
			commandType = GUESS;
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
			emb = embedTest();
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
			textMessage = roll(null);
			break;
		case EWGF:
			emb = getEmbededMove(null); //TODO implement move recognition
			break;
		case GUESS:
			textMessage = "You tried rolling cows and bulls";
		default:
			break;
		}
		

		// @ the user who triggered the command
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

	public Move createMockEWGF() {
		Fighter fighter = new Fighter();
		fighter.setName("Devil Jin");
		fighter.setIconUrl("http://tekken7combo.kagewebsite.com/tpl/img/char/devil-jin.jpg");
		fighter.setTier("S+");

		Move ewgf = new Move();
		ewgf.setName("EWGF");
		ewgf.setGifUrl("https://i.imgur.com/tn3QiLy.gif");
		ewgf.setCommand("f, N, d, d/f+2");
		ewgf.setHitLevel("h");
		ewgf.setDamage("23");
		ewgf.setStartUpFrame("11~15 (14~)");
		ewgf.setBlockFrame("=+5~+6");
		ewgf.setHitFrame("Launch (JG?)");
		ewgf.setCounterHitFrame("Launch (JG?)");
		ewgf.setNotes("DAH!");
		ewgf.setSafeOnBlock(Boolean.TRUE);
		ewgf.setFighter(fighter);

		return ewgf;
	}

	public MessageEmbed getEmbededMove(Move move) {
		move = createMockEWGF();

		String commandTitle = move.getCommand();
		String description = move.getName();
		

		
		String onBlock = move.getBlockFrame();
		
		
		String onHit = move.getHitFrame();
		
		
		String onCounterHit = move.getCounterHitFrame();
		
		
		String property = move.getHitLevel();
		
		
		String damage = move.getDamage();
		
		
		String startup = move.getStartUpFrame();
		
		// Create the EmbedBuilder instance
		EmbedBuilder eb = new EmbedBuilder();

		/*
		 * Set the title: 1. Arg: title as string 2. Arg: URL as string or could also be
		 * null
		 */
		eb.setTitle(commandTitle , "https://google.com");

		/*
		 * Set the color
		 */
		// eb = eb.setColor(Color.red);
		eb.setColor(new Color(0xF40C0C));
		// eb.setColor(new Color(255, 0, 54));

		 
		//Emote emote = event.getJDA().getEmoteById("485509719579033655");
		List<Emote> emotes = event.getJDA().getEmotesByName("d_", true); 
		if(!emotes.isEmpty()) {
			Emote emote = emotes.get(0);
			log.info("Emote name: " + emote.getName());
			log.info("Emote id: " + emote.getId());
			String fullEmojiUrl = "<:"+emote.getName() +":" + emote.getId() + ">";
			log.info("Combined!!:"+ fullEmojiUrl);
		}
		
		
		eb.setDescription("*" + description + "*");
		String commandAsEmoji =  "<:d_:485509719579033655>";
		commandAsEmoji = moveTranslator.translateToEmoji(move, event.getGuild().getEmotes());
		eb.addField(commandAsEmoji , "" , false);
		
		
		
		eb.addField(onBlockTitle, onBlock, true);
		eb.addField(onHitTitle, onHit, true);
		eb.addField(onCounterHitTitle, onCounterHit, true);
		
		eb.addField(propertyTitle, property, true);
		eb.addField(damageTitle, damage, true);
		eb.addField(startupTitle, startup, true);
		
		//TODO get field titles from .properties file


		eb.addBlankField(false);

		/*
		 * Add embed author: 1. Arg: name as string 2. Arg: url as string (can be null)
		 * 3. Arg: icon url as string (can be null)
		 */
		eb.setAuthor(move.getFighter().getName(), null, move.getFighter().getIconUrl()); 

		
		String footer = move.getNotes();
		String footerIconUrl = null;
		
		/*
		 * Set footer: 1. Arg: text as string 2. icon url as string (can be null)
		 */
		eb.setFooter(footer, footerIconUrl);

		/*
		 * Set image: Arg: image url as string
		 */
		eb.setImage(move.getGifUrl());

		/*
		 * Set thumbnail image: Arg: image url as string
		 */
		String thumbnail = UNSAFE_ICON;
		if(move.isSafeOnBlock()) {
			thumbnail = SAFE_ICON;
		}
		eb.setThumbnail(thumbnail);

		return eb.build();

	}
	
	public MessageEmbed embedTest() {
		
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
	//	eb = eb.setColor(Color.red);
		eb.setColor(new Color(0xF40C0C));
	//	eb.setColor(new Color(255, 0, 54));

		/*
		    Set the text of the Embed:
		    Arg: text as string
		 */
		eb.setDescription("*Description*");

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
		eb.setAuthor("name", null, "https://i.makeagif.com/media/7-19-2015/6UTBzE.gif");   // STEVE GIF

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
		eb.setImage("https://im3.ezgif.com/tmp/ezgif-3-1382a2d2f0.gif");

		/*
		    Set thumbnail image:
		    Arg: image url as string
		 */
		eb.setThumbnail("https://vignette.wikia.nocookie.net/tekken/images/9/9f/KazuyaEWGF.gif/revision/latest?cb=20150107125830&path-prefix=en");
		
		
		return eb.build();
	}

	public String roll(Integer maxRoll) {

		int max = 100;
		if (maxRoll != null) {
			max = maxRoll;
		}

		Random rand = new Random();
		int roll = rand.nextInt(max +1); // This results in 0 - max 
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
/*	
	public boolean verifyEmoji(String emoji) {

		Emote emote = jda.getEmoteById("485509719579033655");
		return true;
	}*/

}
