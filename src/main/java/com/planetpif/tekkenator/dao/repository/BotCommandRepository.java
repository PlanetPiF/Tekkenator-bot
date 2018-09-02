package com.planetpif.tekkenator.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.planetpif.tekkenator.model.BotCommand;

@Repository
public interface BotCommandRepository extends CrudRepository<BotCommand,Long> {

}
