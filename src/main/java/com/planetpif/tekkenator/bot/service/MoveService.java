package com.planetpif.tekkenator.bot.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.planetpif.tekkenator.dao.repository.MoveRepository;
import com.planetpif.tekkenator.model.Fighter;
import com.planetpif.tekkenator.model.Move;

public interface MoveService {
	
	
	List<Move> getMovesByFighter(Fighter fighter);

	
}
