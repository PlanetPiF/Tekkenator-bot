package com.planetpif.tekkenator.bot.utils;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.model.Move;

import net.dv8tion.jda.core.entities.MessageEmbed;

@Service
public class EmbedderImpl implements Embedder {

	static final Logger logger = Logger.getLogger(EmbedderImpl.class);
	
	@Override
	public String convertEmoji(String emojiId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageEmbed constructMoveEmbed(Move move) {
		// TODO Auto-generated method stub
		return null;
	}

}
