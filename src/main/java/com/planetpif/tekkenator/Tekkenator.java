package com.planetpif.tekkenator;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.planetpif.tekkenator.bot.JDABot;
import com.planetpif.tekkenator.dao.FighterRepository;
import com.planetpif.tekkenator.dao.MoveRepository;
import com.planetpif.tekkenator.model.Fighter;
import com.planetpif.tekkenator.model.Move;
/**
 * 
 * @author TheAwesomePiF
 *
 */
@SpringBootApplication
@EnableJpaRepositories("com.planetpif.tekkenator")
public class Tekkenator implements CommandLineRunner {

	static final Logger logger = Logger.getLogger(Tekkenator.class);
	
	@Autowired
	DataSource dataSource;

	@Autowired
	private FighterRepository fighterRepository;

	@Autowired
	private MoveRepository moveRepository;

	@Autowired
	private JDABot jdaBot;

/*	@Autowired
	private BotListenterInterface myBotListener;*/

	@Value("${discord.token}")
	private String token;

	public static void main(String[] args) {
		SpringApplication.run(Tekkenator.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.createBot();
		logger.info("End of main reached.");
	}

	public void createBot() {
		//jdaBot.setFighterRepository(fighterRepository);
		//jdaBot.setMoveRepository(moveRepository);
		jdaBot.init(token);
	}

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
		Move ewgf = new Move();
		ewgf.setName("EWGF");
		ewgf.setCommand("cd+2");
		ewgf.setHitFrame("h");
		ewgf.setFighter(devilJin);
		moveRepository.save(ewgf);

		Move hellsweep = new Move();
		hellsweep.setName("Hellsweep");
		hellsweep.setCommand("cd+4");
		hellsweep.setFighter(devilJin);
		moveRepository.save(hellsweep);

		Iterable<Move> movesList = moveRepository.findAll();
		System.out.println("Total Moves: " + moveRepository.count());
		for (Move move : movesList) {
			System.out.println(move.getName() + " belongs to " + move.getFighter().getName());
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

}
