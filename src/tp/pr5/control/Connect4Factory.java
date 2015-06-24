package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.Resources.Counter;
import tp.pr5.logic.Connect4Move;
import tp.pr5.logic.Connect4Rules;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.Move;

public class Connect4Factory implements GameTypeFactory {

	public GameRules createRules() {
		return new Connect4Rules();
	}

	public Move createMove(int col, int row, Counter colour) {
		return new Connect4Move(col, colour);
	}

	public Player createHumanPlayerAtConsole(Scanner in) {
		return new HumanPlayer(false, in, this);
	}
  
	public Player createRandomPlayer() {
		return new RandomConnect4Player();
	}

} 
