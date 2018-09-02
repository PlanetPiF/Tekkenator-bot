package com.planetpif.tekkenator.dao.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.planetpif.tekkenator.model.Fighter;
import com.planetpif.tekkenator.model.Move;

/**
 * This repository uses Spring-JPA to automatically build queries based on the method's name.
 * For more info: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
 * @author Ivaylo Lesev
 *
 */
@Repository
public interface MoveRepository extends CrudRepository<Move, Long>{

    List<Move> getMovesByFighter(Fighter fighter);
    
    Move getMoveByAlias(String alias);
    
    Move findMoveByAlias(String alias);
    
    Move findMoveByName(String name);
    
   // Move findMoveByNameOrAlias(String Name);

}
