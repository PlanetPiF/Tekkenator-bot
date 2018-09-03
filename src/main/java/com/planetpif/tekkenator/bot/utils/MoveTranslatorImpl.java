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

	@Override
	public String translateToEmoji(Move move) {
		return this.translateToEmoji(move.getCommand());
	}
	
	// EMOJI PLACEHOLDERS
	private static final String DOWN_EMOJI = "[DOWN]";
	private static final String FORWARD_EMOJI = "[FOWARD]";
	private static final String UP_EMOJI = "[UP]";
	private static final String BACK_EMOJI = "[BACK]";
	
	private static final String DOWN_FORWARD_EMOJI = "[DOWN_FORWARD]";
	private static final String DOWN_BACK_EMOJI = "[DOWN_BACK]";

	private static final String UP_FORWARD = "[UP_FORWARD]";
	private static final String UP_BACK = "[UP_BACK]";
	
	private static final String NEUTRAL_EMOJI = "[NEUTRAL]";
	
	private static final String ONE_BTN_EMOJI = "[1]";
	private static final String TWO_BTN_EMOJI = "[2]";
	private static final String THREE_BTN_EMOJI = "[3]";
	private static final String FOUR_BTN_EMOJI = "[4]";
	
	// combinations
	private static final String ONE_TWO_BTN_EMOJI = "[1+2]";
	private static final String ONE_THREE_BTN_EMOJI = "[1+3]";
	private static final String ONE_FOUR_BTN_EMOJI = "[1+4]";
	
	private static final String TWO_THREE_BTN_EMOJI = "[2+3]";  // exists?
	private static final String TWO_FOUR_BTN_EMOJI = "[2+4]";

	private static final String THREE_FOUR_BTN_EMOJI = "[3+4]";
	
	

	@Override
	public String translateToEmoji(String command) {
		
		log.info("----- Translating to Emoji: " + command);
		
		command = command.toLowerCase();
		// remove ","
	//	command = command.replace(",", "");
		// Diagonals first
		command = command.replace("d/f", DOWN_FORWARD_EMOJI);
		command = command.replace("d/b", DOWN_BACK_EMOJI);
		command = command.replace("u/f", UP_FORWARD);
		command = command.replace("u/b", UP_BACK);
		// Directions second
		command = command.replace("d", DOWN_EMOJI);
		command = command.replace("f", FORWARD_EMOJI);
		command = command.replace("u", UP_EMOJI);
		command = command.replace("b", BACK_EMOJI);
		command = command.replace("n", NEUTRAL_EMOJI);
		
		// Combinations
		command = command.replace("1+2", ONE_TWO_BTN_EMOJI);
		command = command.replace("1+3", ONE_THREE_BTN_EMOJI);
		command = command.replace("1+4", ONE_FOUR_BTN_EMOJI);
		
		command = command.replace("2+3", TWO_THREE_BTN_EMOJI);
		command = command.replace("2+4", TWO_FOUR_BTN_EMOJI);
		
		command = command.replace("3+4", THREE_FOUR_BTN_EMOJI);
		
		// Buttons (punches 1,2 and kicks 3,4)
		command = command.replace("1", ONE_BTN_EMOJI);
		command = command.replace("2", TWO_BTN_EMOJI);
		command = command.replace("3", THREE_BTN_EMOJI);
		command = command.replace("4", FOUR_BTN_EMOJI);
		


		

		log.info("---- Translated to emoji: ");
		log.info(command);
		
		// TODO Auto-generated method stub
		return command;
	}

}
