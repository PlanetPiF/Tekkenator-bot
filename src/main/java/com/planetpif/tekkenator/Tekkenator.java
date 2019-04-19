package com.planetpif.tekkenator;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.planetpif.tekkenator.bot.DiscordJdaBot;
import com.planetpif.tekkenator.bot.service.MoveService;
import com.planetpif.tekkenator.bot.utils.MoveTranslator;
import com.planetpif.tekkenator.bot.utils.RandomUtils;
import com.planetpif.tekkenator.bot.utils.games.CowsAndBullsCheckerImpl;
import com.planetpif.tekkenator.dao.repository.CNBRepository;
import com.planetpif.tekkenator.dao.repository.FighterRepository;
import com.planetpif.tekkenator.dao.repository.MoveRepository;
/**
 * This is where the bot actually goes online. Run Tekkenator.main as a java program to start the bot.
 * (If packed into a jar, run via command line:   java -jar Tekkenator.jar)
 * @author TheAwesomePiF
 *
 */
@SpringBootApplication
@EnableJpaRepositories("com.planetpif.tekkenator")
public class Tekkenator implements CommandLineRunner {

	static final Logger log = Logger.getLogger(Tekkenator.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private DiscordJdaBot discordBot;
	
	@Autowired
	private RandomUtils utils;
	
	@Autowired
	private MoveRepository moveRepository;
	
	@Autowired
	private CNBRepository cnbRepository;
	
	@Autowired
	private FighterRepository fighterRepository;
	
	@Autowired
	private MoveService moveService;
	
	@Autowired
	private MoveTranslator moveTranslator;

	@Value("${discord.token}")
	private String token;

	public static void main(String[] args) {
		CowsAndBullsCheckerImpl checker = new CowsAndBullsCheckerImpl();
		checker.findCowsAndBulls("asdf", "asfd");
		SpringApplication.run(Tekkenator.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//this.executeTestStuff();
		this.createBot();
	}
	
	public void executeTestStuff() {
		
		
		moveTranslator.translate("f, n, d, D/F+2");
		
		moveTranslator.translate("cd+4");
		
		//moveTranslator.translateToEmoji("f, n, d, D/F+2");  //TODO make event / guild / emojis accessible everywhere!
		
		/*Fighter dj = fighterRepository.findById(147L).get();
		
		List<Move> moves = moveService.getMovesByFighter(dj);
		for(Move move: moves) {
			log.info("Move found! - " + move.getName());
		}
		
		String nameOrAlias = "hs";
		//Move hs = moveRepository.findMoveByNameOrAliasContaining(nameOrAlias, nameOrAlias);
		
		Move hs = moveRepository.findByNameForFighter(nameOrAlias, dj);
		log.info("DJ's low is called: " + hs.getName() + " Alias: " + hs.getAlias());
		
		*/
		
		
		utils.addMockCommands(Boolean.FALSE);
	}

	public void createBot() {
		discordBot.init(token);
	}

}
