package com.planetpif.tekkenator.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.planetpif.tekkenator.model.Fighter;

@Repository
public interface FighterRepository extends CrudRepository<Fighter,Long> {

}
