package tp.pr4.control;

import tp.pr4.logic.Board;
import tp.pr4.logic.Connect4Move;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Move;
import tp.pr4.Resources.Resources;


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