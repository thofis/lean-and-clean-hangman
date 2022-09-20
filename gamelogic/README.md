# Game-Logic-Module

## Responsibilities

Contains everything related to the logic of the game.

* Selecting and storing a search term
  * getting the number of available search terms from persistence provider
  * choose a (random) search term via persistence provider
* Keeping track of the number of wrong guesses
* Processing a guessed character as entered within the ui onto the search term
  * on correct guess: unhide the locations of this character in the search term
  * on wrong guess: increase the number of wrong guesses
* Check for end of game
  * won: all characters have been guessed correctly
  * lost: maximum number of wrong guesses exceeded
