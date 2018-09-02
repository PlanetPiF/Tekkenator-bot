package com.planetpif.tekkenator.bot.service;

import java.util.List;

import com.planetpif.tekkenator.model.Fighter;
import com.planetpif.tekkenator.model.Move;

public interface MoveService {
	
	
	List<Move> getMovesByFighter(Fighter fighter);

	
}
