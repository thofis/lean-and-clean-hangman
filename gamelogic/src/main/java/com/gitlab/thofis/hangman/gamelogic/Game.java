package com.gitlab.thofis.hangman.gamelogic;

import static com.gitlab.thofis.hangman.gamelogic.Game.State.*;

public class Game {

	private State state;

	public State getState() {
		return state;
	}

	public boolean isOver() {
		return state.gameOver();
	}

	public enum State {
		LOST,
		WON,
		IN_PROGRESS;

		boolean gameOver() {
			return  ordinal() == WON.ordinal() || ordinal() == LOST.ordinal();
		}
	}

	static final int INITIAL_NUMBER_OF_GUESSES = 6;

	static final char HIDDEN_CHARACTER = '*';

	public final String searchTerm;

	private String hiddenTerm;


	public int remainingGuesses;

	public Game(String searchterm) {
		this.searchTerm = searchterm.toLowerCase();
		this.hiddenTerm = hide(searchterm);
		this.remainingGuesses = INITIAL_NUMBER_OF_GUESSES;
		this.state = IN_PROGRESS;
	}

	private String hide(String searchterm) {
		return Character.toString(HIDDEN_CHARACTER).repeat(searchterm.length());
	}

	public int getRemainingGuesses() {
		return remainingGuesses;
	}

	public String getHiddenTerm() {
		return hiddenTerm;
	}

	public boolean guess(char character) {
		String oldTerm = hiddenTerm;
		StringBuilder term = new StringBuilder(hiddenTerm);
		for (int i = 0; i < searchTerm.length(); i++) {
			if (searchTerm.charAt(i) == Character.toLowerCase(character)) {
				term.setCharAt(i, Character.toLowerCase(character));
			}
		}
		hiddenTerm = term.toString();
		boolean correctGuess = !oldTerm.equals(hiddenTerm);
		if (!correctGuess) {
			remainingGuesses--;
		}
		checkForWin();
		checkForLost();
		return correctGuess;
	}

	private void checkForLost() {
		if (remainingGuesses == 0) {
			state = LOST;
		}
	}

	private void checkForWin() {
		if (searchTerm.equals(hiddenTerm)) {
			state = WON;
		}
	}
}
