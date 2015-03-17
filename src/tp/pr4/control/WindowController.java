package tp.pr4.control;

import tp.pr4.logic.Counter;
import tp.pr4.logic.Game;
import tp.pr4.logic.GameType;

public class WindowController {
	
	public WindowController(GameTypeFactory factory, Game g) {
		// Constructor
	}
	
	void changeGame(GameType gameType, int dimX, int dimY) {
		// Change to a new game.
	}
	void makeMove(int col, int row, Counter turn) {
		
	}
	
	void randomMove(Counter player) {
		// Make a random move.
	}
	
	void requestQuit() {
		// Quit the application.
	}
	
	void reset() {
		// Reset the current game.
	}
	
	void run() {
		// Creates the window view.
	}
	
	void undo() {
		// Undo the last move of the currently played game.
	}
	
}
