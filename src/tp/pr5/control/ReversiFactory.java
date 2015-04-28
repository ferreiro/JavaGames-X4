package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.logic.Counter;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReversiMove;
import tp.pr5.logic.ReversiRules;

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
