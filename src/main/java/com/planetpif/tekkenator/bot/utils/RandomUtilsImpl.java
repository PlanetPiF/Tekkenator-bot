package com.planetpif.tekkenator.bot.utils;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetpif.tekkenator.bot.service.BotCommandService;
import com.planetpif.tekkenator.dao.repository.FighterRepository;
import com.planetpif.tekkenator.dao.repository.MoveRepository;
import com.planetpif.tekkenator.model.Fighter;
import com.planetpif.tekkenator.model.Move;

@Component
public class RandomUtilsImpl implements RandomUtils {
	
	static final Logger log = Logger.getLogger(RandomUtilsImpl.class);
	
	@Autowired
	private FighterRepository fighterRepository;
	
	@Autowired
	private MoveRepository moveRepository;
	
	@Autowired
	private BotCommandService botCommandService;
	
	public void addFightesAndMovesToDb(Boolean deleteAfter) {
		/// FOR TESTING PURPOSES
		
		// ADD FIGHTERS + MOVEs

		// ------------------ FIGHTERS ---------------------

		// Add Fighter - Eddy
		Fighter eddy = new Fighter();
		eddy.setName("Eddy Gordo");
		eddy.setTier("A");
		fighterRepository.save(eddy);
		// Add Fighter - Devil Jin
		Fighter devilJin = new Fighter();
		devilJin.setName("Devil Jin");
		devilJin.setTier("S+");
		fighterRepository.save(devilJin);

		// ------------------ MOVES ---------------------

		// EWGF - Devil Jin
		Move ewgf = this.createMockEWGF(devilJin);
		moveRepository.save(ewgf);

		Move hellsweep = createMockHellSweep(devilJin);
		moveRepository.save(hellsweep);

		Iterable<Move> movesList = moveRepository.findAll();
		log.info("Total Moves: " + moveRepository.count());
		for (Move move : movesList) {
			log.info(move.getName() + " belongs to " + move.getFighter().getName());
		}

		if (deleteAfter) {
			moveRepository.delete(ewgf);
			moveRepository.delete(hellsweep);
		}

		if (deleteAfter) {
			fighterRepository.delete(eddy);
			fighterRepository.delete(devilJin);
		}
	}
	

	public Move createMockEWGF(Fighter fighter) {
		fighter.setName("Devil Jin");
		fighter.setIconUrl("http://tekken7combo.kagewebsite.com/tpl/img/char/devil-jin.jpg");
		fighter.setTier("S+");

		Move ewgf = new Move();
		ewgf.setName("EWGF");
		ewgf.setGifUrl("https://i.imgur.com/tn3QiLy.gif");
		ewgf.setCommand("f, n, d, d/f+2");
		ewgf.setHitLevel("h");
		ewgf.setDamage("23");
		ewgf.setStartUpFrame("11~15 (14~)");
		ewgf.setBlockFrame("=+5~+6");
		ewgf.setHitFrame("Launch (JG?)");
		ewgf.setCounterHitFrame("Launch (JG?)");
		ewgf.setNotes("DAH!");
		ewgf.setSafeOnBlock(Boolean.TRUE);
		ewgf.setFighter(fighter);

		return ewgf;
	}
	
	public Move createMockHellSweep (Fighter fighter) {

		Move ewgf = new Move();
		ewgf.setName("Hellsweep");
		ewgf.setGifUrl("https://thumbs.gfycat.com/QuarterlyPeskyAiredaleterrier-size_restricted.gif");
		ewgf.setCommand("f, n, d, d/f+4");
		ewgf.setHitLevel("L");
		ewgf.setDamage("10");
		ewgf.setStartUpFrame("16(19~)");
		ewgf.setBlockFrame("-23");
		ewgf.setHitFrame("KND");
		ewgf.setCounterHitFrame("KND");
		ewgf.setNotes("The unseen low is the deadliest!");
		ewgf.setSafeOnBlock(Boolean.FALSE);
		ewgf.setFighter(fighter);

		return ewgf;
	}

	@Override
	public void addMockCommands(Boolean active) {
		if (active) {
			botCommandService.generateMockCommands();
		}
	}

}
