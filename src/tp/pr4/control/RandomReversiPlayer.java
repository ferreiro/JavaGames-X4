package tp.pr4.control;

import tp.pr4.Resources.Resources;
import tp.pr4.logic.Board;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Move;
import tp.pr4.logic.ReversiMove;

public class RandomReversiPlayer implements Player {

	@Override
	public Move getMove(Board board, Counter counter) {
		boolean valid = false;
		int column = 0, row = 0;
		
		do { 
			row    = (int) ((Math.random() * Resources.DIMY_REVERSI) + 1);
			column = (int) ((Math.random() * Resources.DIMX_REVERSI) + 1);
			 
			if (board.getPosition(column, row) == Counter.EMPTY) {
				valid = true;
			}
		}  while(!valid);
		
		return new ReversiMove(column, row, counter);
	}
}
