package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.Counter;
import tp.pr4.logic.GameRules;
import tp.pr4.logic.Move;
import tp.pr4.logic.ReversiMove;
import tp.pr4.logic.ReversiRules;

public class ReversiFactory implements GameTypeFactory {

	@Override
	public GameRules createRules() {
		return new ReversiRules();
	}
	
	public Move createMove(int col, int row, Counter colour) {
		return new ReversiMove(col, row, colour);
	}

	public Player createHumanPlayerAtConsole(Scanner in) {
		return new HumanPlayer(false, in, this);
	}

	public Player createRandomPlayer() {
		return new RandomReversiPlayer();
	}

}
