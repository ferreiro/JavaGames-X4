package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.Resources.Resources;
import tp.pr4.logic.Counter;
import tp.pr4.logic.GameRules;
import tp.pr4.logic.GravityMove;
import tp.pr4.logic.GravityRules;
import tp.pr4.logic.Move;

public class GravityFactory implements GameTypeFactory{
	private int dimX, dimY;
	

	public GravityFactory(int x, int y) {
		this.dimX = x;
		this.dimY = y;
	}

	public GameRules createRules() {	
		return new GravityRules(dimX, dimY);
	}

	public Move createMove(int col, int row, Counter colour) {
		return new GravityMove(col, row, colour);
	}

	public Player createHumanPlayerAtConsole(Scanner in) {
		return new HumanPlayer(true, in, this);
	}

	public Player createRandomPlayer() {
		return new RandomGravityPlayer();
	}

}
