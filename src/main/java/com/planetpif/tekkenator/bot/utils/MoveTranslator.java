package com.planetpif.tekkenator.bot.utils;

import java.util.List;

import com.planetpif.tekkenator.model.Move;

import net.dv8tion.jda.core.entities.Emote;

public interface MoveTranslator {

	String translate(Move move);
	
	String translate(String command);
	
	String translateToEmoji(Move move, List<Emote> emotes);
	
	String translateToEmoji(String command, List<Emote> emotes);
}
