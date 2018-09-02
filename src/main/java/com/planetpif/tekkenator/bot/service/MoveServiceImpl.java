package com.planetpif.tekkenator.bot.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.dao.repository.MoveRepository;
import com.planetpif.tekkenator.model.Fighter;
import com.planetpif.tekkenator.model.Move;

@Service
public class MoveServiceImpl implements MoveService{
	
	static final Logger logger = Logger.getLogger(MoveServiceImpl.class);

	@Autowired
	private MoveRepository moveRepository;

	@Override
	public List<Move> getMovesByFighter(Fighter fighter) {
		return moveRepository.getMovesByFighter(fighter);
	}
	
	
}
