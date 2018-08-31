package com.planetpif.tekkenator;

import javax.security.auth.login.LoginException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.dao.FighterRepository;
import com.planetpif.tekkenator.dao.MoveRepository;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

@Service
public class JDABot implements DiscordBot {

    private JDA jda;
    
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

	public boolean init(BotListenterInterface listener, String token) {
        try {
            jda = new JDABuilder(AccountType.BOT)
                    .setToken(token)
                    .setGame(Game.playing("Tekken 7"))
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .addEventListener(listener)
                    .build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        if(moveRepository == null) {
        	System.out.println("moveRepository is NULL on init");
        }
        
        return true;
    }

    public void sendMessage(String guild, String channel, String message) {
        try {
            jda
                    .getGuildById(guild)
                    .getTextChannelById(channel)
                    .sendMessage(message)
                    .queue();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
