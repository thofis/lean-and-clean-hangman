package com.gitlab.thofis.hangman.gamelogic;

import java.util.stream.Stream;

import com.gitlab.thofis.hangman.gamelogic.Game.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class GameTest {

	public static final String SEARCHTERM = "dummy";

	private Game game;


	@BeforeEach
	void before() {
		game = new Game(SEARCHTERM);
	}

	@Test
	void should_store_searchterm() {
		assertThat(game.searchTerm).isEqualTo(SEARCHTERM);
	}

	@Test
	void should_have_correct_number_of_remaining_guesses_at_start() {
		assertThat(game.getRemainingGuesses()).isEqualTo(Game.INITIAL_NUMBER_OF_GUESSES);
	}

	@Test
	void should_have_all_characters_hidden_at_start() {
		assertThat(game.getHiddenTerm()).isEqualTo(Character.toString(Game.HIDDEN_CHARACTER).repeat(SEARCHTERM.length()));
	}

	@Test
	void should_reveal_correctly_guessed_character() {
		boolean correctGuess = game.guess('d');
		assertThat(correctGuess).isTrue();
		assertThat(game.getHiddenTerm()).isEqualTo("d****");
	}

	@Test
	void should_decrement_number_of_guesses_on_fail() {
		int remainingGuessesBefore = game.remainingGuesses;
		boolean correctGuess = game.guess('x');
		assertThat(correctGuess).isFalse();
		assertThat(game.remainingGuesses).isEqualTo(remainingGuessesBefore - 1);
	}

	@Test
	void should_ignore_case_in_searchterm() {
		game = new Game("Dummy");
		game.guess('d');
		assertThat(game.getHiddenTerm()).isEqualTo("d****");
	}
	@Test
	void should_ignore_case_in_guessed_character() {
		game.guess('D');
		assertThat(game.getHiddenTerm()).isEqualTo("d****");
	}

	@Test
	void should_have_in_progress_state_on_start() {
		assertThat(game.getState()).isEqualTo(State.IN_PROGRESS);
	}

	@Test
	void should_have_in_progress_state_during_game() {
		game.guess('d');
		game.guess('m');
		game.guess('y');

		Stream.iterate(1, i -> i+1)
				.limit(Game.INITIAL_NUMBER_OF_GUESSES - 1)
				.forEach(i -> game.guess('x'));

		assertThat(game.getState()).isEqualTo(State.IN_PROGRESS);
	}

	@Test
	void should_have_won_state_after_game_won() {
		guessesToWinGame();
		assertThat(game.getState()).isEqualTo(State.WON);
	}

	private void guessesToWinGame() {
		game.guess('d');
		game.guess('u');
		game.guess('m');
		game.guess('m');
		game.guess('y');
	}

	@Test
	void should_have_lost_state_after_game_lost() {
		Stream.iterate(1, i -> i+1)
						.limit(Game.INITIAL_NUMBER_OF_GUESSES)
								.forEach(i -> game.guess('x'));
		assertThat(game.getState()).isEqualTo(State.LOST);
	}

	@Test
	void should_have_game_over_true_on_won_or_lost() {
		guessesToWinGame();
		assertThat(game.isOver()).isTrue();
	}


}
