package com.planetpif.tekkenator.bot.utils;

import java.text.MessageFormat;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.planetpif.tekkenator.model.Move;

@Service
public class MoveTranslatorImpl implements MoveTranslator {

	static final Logger log = Logger.getLogger(MoveTranslatorImpl.class);

	@Override
	public String translate(Move move) {
		return this.translate(move.getCommand());
	}

	@Override
	public String translate(String command) {
//		command = command.trim(); //  trim? 

		log.info("----------------------------------------");
		log.info(MessageFormat.format("-- Translation of command {0} started ---", command));

		// make everything lower case
		command = command.toLowerCase();

		// Crouch dash (cd)
		command = command.replaceAll("cd", "f, n, d, df");

		// Circle movements - qcf , qcb, hcf, hcb
		command = command.replaceAll("qcf", "d, df, f");
		command = command.replaceAll("qcb", "d, db, b");
		command = command.replaceAll("hcf", "b, db, d, df, f");
		command = command.replaceAll("hcb", "f, df, d, db, b");

		// Remove " " and /
		command = command.replace(" ", "");
		command = command.replace("/", "");
		// Remove , and .
		command = command.replace(",", "");
		command = command.replace(".", "");
		
		// Remove +
		command = command.replace("+", "");
		
		// Remove > (delay / hitconfirmable character notation)
		command = command.replace(">", "");
		
		// wr, ss, ws, fc 
		
		
		log.info("Command after translation: ");
		log.info(command);
		log.info("----------------------------------------"	);
		return command;
	}
	
	private String getSimilarMoves(String command) {
		//TODO fill
		return command;
	}

}
