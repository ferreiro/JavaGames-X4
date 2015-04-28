package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.ComplicaMove;
import tp.pr5.logic.ComplicaRules;
import tp.pr5.logic.Counter;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.Move;

public class ComplicaFactory implements GameTypeFactory {

	public GameRules createRules() {
		return new ComplicaRules();
	}

	public Move createMove(int col, int row, Counter colour) {
		return new ComplicaMove(col, colour);
	}

	public Player createHumanPlayerAtConsole(Scanner in) {
		return new HumanPlayer(false, in, this);
	}

	public Player createRandomPlayer() {
		return new RandomComplicaPlayer();
	}
 
}
