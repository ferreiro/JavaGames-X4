package tp.pr5.control;

import tp.pr5.logic.Board;
import tp.pr5.logic.Connect4Move;
import tp.pr5.logic.Move;
import tp.pr5.Resources.Counter;
import tp.pr5.Resources.Resources;


public class RandomConnect4Player implements Player{

	public Move getMove(Board board, Counter counter) {
		int column;
		boolean valid = false;
		Move randomMove = null;
		
		do {
			column = (int) ((Math.random() * Resources.DIMX_CONNECT4) + 1); // Generates Random Number
			
			if (!Resources.fullColumn(column, board)){
				valid = true;
				randomMove = new Connect4Move(column, counter);
			}
		} while (!valid);
		
		return randomMove;
	}

}