package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.Resources.Counter;
import tp.pr5.logic.GameRules;
import tp.pr5.logic.GravityMove;
import tp.pr5.logic.GravityRules;
import tp.pr5.logic.Move;

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
