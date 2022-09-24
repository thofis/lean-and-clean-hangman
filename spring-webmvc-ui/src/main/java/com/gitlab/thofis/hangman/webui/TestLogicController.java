package com.gitlab.thofis.hangman.webui;

import java.util.Map;

import com.gitlab.thofis.hangman.gamelogic.Game;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestLogicController {

	private static Game game;

	@GetMapping
	public ModelAndView index() {
		game = new Game("dummy");
		return new ModelAndView("testlogic", "game", game);
	}

	@PostMapping("/start")
	public ModelAndView start(@ModelAttribute SearchTermHolder searchTermHolder) {
		game = new Game(searchTermHolder.getSearchTerm());
		return new ModelAndView("testlogic", "game",  game);
	}

	@PostMapping(value = "/guess", consumes = "application/x-www-form-urlencoded;charset=UTF-8")
	public ModelAndView guess(@ModelAttribute CharacterHolder characterHolder) {
		String character = characterHolder.getCharacter();
		if (character.length() == 0) {
			throw new IllegalArgumentException("No character entered");
		}
		char guessedCharacter = character.charAt(0);
		game.guess(guessedCharacter);
		return new ModelAndView("testlogic", "game", game);
	}
}

class CharacterHolder {
	private String character;

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}
}

class SearchTermHolder {
	private String searchTerm;

	public String getSearchTerm() {
		return searchTerm;
	}

	public void setSearchTerm(String searchTerm) {
		this.searchTerm = searchTerm;
	}
}
