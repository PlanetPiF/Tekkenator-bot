package com.planetpif.tekkenator;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.planetpif.tekkenator.dao.FighterRepository;
import com.planetpif.tekkenator.dao.MoveRepository;
import com.planetpif.tekkenator.model.Fighter;
import com.planetpif.tekkenator.model.Move;

@SpringBootApplication
@EnableJpaRepositories("com.planetpif.tekkenator.dao")
public class TekkenatorApplication implements CommandLineRunner {

	@Autowired
	DataSource dataSource;
	
	@Autowired
	private FighterRepository fighterRepository;
	
	@Autowired
	private MoveRepository moveRepository;

	public static void main(String[] args) {
		SpringApplication.run(TekkenatorApplication.class, args);
	}
	
	private static final Boolean DELETE_FIGHTERS= Boolean.TRUE;
	private static final Boolean DELETE_MOVES= Boolean.TRUE;
	

	@Override
	public void run(String... args) throws Exception {
		
		/// TEST FIGHTERS + MOVE CREATION

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
		ewgf.setFighter(devilJin);
		moveRepository.save(ewgf);
		
		Move hellsweep = new Move();
		hellsweep.setName("Hellsweep");
		hellsweep.setCommand("cd+4");
		hellsweep.setFighter(devilJin);
		moveRepository.save(hellsweep);
		
		
		
		
		Iterable<Move> movesList = moveRepository.findAll();
		System.out.println("Total Moves: " + moveRepository.count());
		for(Move move:movesList){
			System.out.println(move.getName() + " belongs to " + move.getFighter().getName());
		}
		
		
		
		if(DELETE_MOVES) {
			moveRepository.delete(ewgf);
			moveRepository.delete(hellsweep);
		}
		
		if(DELETE_FIGHTERS) {
			fighterRepository.delete(eddy);
			fighterRepository.delete(devilJin);
		}
		
		
		

	}
	

}
