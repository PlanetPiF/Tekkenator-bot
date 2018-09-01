package com.planetpif.tekkenator.bot.utils;

import com.planetpif.tekkenator.model.Move;

import net.dv8tion.jda.core.entities.MessageEmbed;

public interface Embedder {
	
	public String convertEmoji(String emojiId);

	public MessageEmbed constructMoveEmbed(Move move);
	
}
