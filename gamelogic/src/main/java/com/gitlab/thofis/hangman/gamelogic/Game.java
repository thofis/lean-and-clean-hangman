package com.gitlab.thofis.hangman.gamelogic;

public class Game {

	static final int INITIAL_NUMBER_OF_GUESSES = 6;

	static final String HIDDEN_CHARACTER = "*";

	public final String searchterm;

	private String hiddenterm;


	public int remainingGuesses;

	public Game(String searchterm) {
		this.searchterm = searchterm;
		this.hiddenterm = hide(searchterm);
		this.remainingGuesses = INITIAL_NUMBER_OF_GUESSES;
	}

	private String hide(String searchterm) {
		var hiddenterm = "";
		for (int i = 0; i < searchterm.length(); i++) {
			hiddenterm += HIDDEN_CHARACTER;
		}
		return hiddenterm;
	}

	public int getRemainingGuesses() {
		return remainingGuesses;
	}

	public String getHiddenTerm() {
		return hiddenterm;
	}

	public boolean guess(char character) {
		String oldTerm = hiddenterm;
		StringBuilder term = new StringBuilder(hiddenterm);
		for (int i = 0; i < searchterm.length(); i++) {
			if (searchterm.charAt(i) == character) {
				term.setCharAt(i, character);
			}
		}
		hiddenterm = term.toString();
		return !oldTerm.equals(hiddenterm);
	}
}
