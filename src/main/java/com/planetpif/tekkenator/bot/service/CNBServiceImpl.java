package com.planetpif.tekkenator.bot.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.dao.repository.CNBRepository;
import com.planetpif.tekkenator.model.CNBInfo;

@Service
public class CNBServiceImpl implements CNBService {

	static final Logger log = Logger.getLogger(CNBServiceImpl.class);

	@Autowired
	private CNBRepository cnbRepository;

	@Override
	public boolean saveCBNInfo(CNBInfo cnbInfo) {
		cnbRepository.save(cnbInfo);
		return true;
	}

	@Override
	public CNBInfo findByProperty(String property) {
		return cnbRepository.findCNBInfoByProperty(property);
	}

}
