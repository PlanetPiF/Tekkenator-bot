package com.planetpif.tekkenator.bot;

import javax.security.auth.login.LoginException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.dao.repository.FighterRepository;
import com.planetpif.tekkenator.dao.repository.MoveRepository;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

@Service
public class JDABot implements DiscordJdaBot {

	static final Logger log = Logger.getLogger(JDABot.class);

	private JDA jda;

	public JDA getJda() {
		return jda;
	}

	public void setJda(JDA jda) {
		this.jda = jda;
	}

	@Autowired
	DataSource dataSource;

	@Autowired
	private FighterRepository fighterRepository;

	@Autowired
	private MoveRepository moveRepository;

	public FighterRepository getFighterRepository() {
		return fighterRepository;
	}

	public void setFighterRepository(FighterRepository fighterRepository) {
		this.fighterRepository = fighterRepository;
	}

	public MoveRepository getMoveRepository() {
		return moveRepository;
	}

	public void setMoveRepository(MoveRepository moveRepository) {
		this.moveRepository = moveRepository;
	}

	@Autowired
	private BotListenterInterface myBotListener;

	@Override
	public boolean init(String token) {
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(token).setGame(Game.playing("Tekken 7"))
					.setStatus(OnlineStatus.DO_NOT_DISTURB).addEventListener(myBotListener).build();
		} catch (LoginException e) {
			log.warn("Could not init JDA.", e);
		}

		return true;
	}

	public void sendMessage(String channel, String message) {
		try {
			jda.getTextChannelById(channel).sendMessage(message).queue();
		} catch (Exception e) {
			log.warn("Could not send message.", e);
		}
	}
}
