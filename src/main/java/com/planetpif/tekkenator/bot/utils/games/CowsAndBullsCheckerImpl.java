package com.planetpif.tekkenator.bot.utils.games;

import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class CowsAndBullsCheckerImpl implements CowsAndBullsChecker {

	static final String WORD = "WORD";

	public String findCowsAndBulls(String word, String guess) {

		// Total cows and bulls count
		int cows = 0;
		int bulls = 0;
		
		// Avoid case mismatch
		word = word.toLowerCase();
		guess = guess.toLowerCase();

		// Avoid non-isogram words
		if(!isWordIsogram(word)) {
			return "Word is not an Isogram";
		}
		
		if(!isWordIsogram(guess)) {
			return "Guess is not an Isogram";
		}
		
		
		// validate guess length
		if(guess.length() > word.length()) {
			guess = guess.substring(0, word.length());
		}
		
		// Loop Guess
		for (int guessPosition = 0; guessPosition < guess.length(); guessPosition++) {

			// Loop Word
			for (int wordPosition = 0; wordPosition < word.length(); wordPosition++) {

				// If 1
				if (guess.charAt(guessPosition) == word.charAt(wordPosition)) {
					// If 2
					if (guessPosition == wordPosition) {
						// it's a bull
						bulls++;
					} else {
						// it's a cow
						cows++;
					}
				}
			}
		}

		String result = "Cows: " + cows + " ; Bulls: " + bulls;
		System.out.println(result);
		return result;
	}
	
	boolean isWordIsogram(String word) {
		
		// Validate word input to prevent errors
		if(word == null || word.isEmpty()) {
			return false;
		}
		
		// Break word into letters and sort them
		char letters[] = word.toCharArray(); 
		Arrays.sort(letters);
		
		// Check if each letter is different from the next one in the sorted array
		for(int i = 0; i < letters.length - 1 ; i++) {
			if (letters[i] == letters[i+1]) {
				return false;
			}
		}
		
		// If no problems were found previously then the word is an Isogram
		return true;
	}

}
