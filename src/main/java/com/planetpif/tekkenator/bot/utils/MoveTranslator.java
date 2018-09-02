package com.planetpif.tekkenator.bot.utils;

import com.planetpif.tekkenator.model.Move;

public interface MoveTranslator {

	String translate(Move move);
	
	String translate(String command);
}
