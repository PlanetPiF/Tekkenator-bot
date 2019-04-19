package com.planetpif.tekkenator.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.planetpif.tekkenator.model.CNBInfo;

@Repository
public interface CNBRepository extends CrudRepository<CNBInfo,Long> {

}
