package com.planetpif.tekkenator.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.planetpif.tekkenator.model.Fighter;
import com.planetpif.tekkenator.model.Move;

@Repository
public interface MoveRepository extends CrudRepository<Move, Long>{

	// List<Move> listMovesForFighter(Fighter fighter);  //TODO make this work
}
