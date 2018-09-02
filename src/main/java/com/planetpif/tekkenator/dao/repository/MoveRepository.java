package com.planetpif.tekkenator.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
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
    
  //TODO handle multiple moves with same name ?
    Move findMoveByName(String name);   
    Move findMoveByAliasContaining(String alias);
    Move findMoveByNameOrAliasContaining(String name, String alias);
    
    /**
     * Find for a MOVE for a specified Fighter via either same name or similar alias
     * @param moveNameOrAlias
     * @param fighter
     * @return
     */
    @Query("SELECT m FROM Move m WHERE m.fighter.id = :#{#fighter.id} AND m.alias LIKE CONCAT('%',:moveNameOrAlias,'%') ")
    Move findByNameForFighter( @Param("moveNameOrAlias") String moveNameOrAlias, @Param("fighter") Fighter fighter);
    

}
