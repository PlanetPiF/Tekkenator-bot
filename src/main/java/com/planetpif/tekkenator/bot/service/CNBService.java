package com.planetpif.tekkenator.bot.service;

import com.planetpif.tekkenator.model.CNBInfo;

public interface CNBService {
	
	CNBInfo findByProperty(String property);

	boolean saveCBNInfo(CNBInfo cnbInfo);
	
}
