package com.gitlab.thofis.hangman.gamelogic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class GameTest {

	public static final String SEARCHTERM = "dummy";

	private Game game;


	@BeforeEach
	void before() {
		game = new Game(SEARCHTERM);
	}

	@Test
	void should_store_searchterm() {
		assertThat(game.searchterm).isEqualTo(SEARCHTERM);
	}

	@Test
	void should_have_correct_number_of_remaining_guesses_at_start() {
		assertThat(game.getRemainingGuesses()).isEqualTo(Game.INITIAL_NUMBER_OF_GUESSES);
	}

	@Test
	void should_have_all_characters_hidden_at_start() {
		assertThat(game.getHiddenTerm()).isEqualTo(Game.HIDDEN_CHARACTER.repeat(SEARCHTERM.length()));
	}

	@Test
	void should_reveal_correctly_guessed_character() {
		boolean correctGuess = game.guess('d');
		assertThat(correctGuess).isTrue();
		assertThat(game.getHiddenTerm()).isEqualTo("d****");
	}

}
