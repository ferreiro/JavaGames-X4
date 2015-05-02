package tp.pr5.control;

import tp.pr5.Resources.Resources;
import tp.pr5.logic.Board;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReversiMove;

public class RandomReversiPlayer implements Player {

	@Override
	public Move getMove(Board board, Counter counter) {
		boolean valid = false, moved = false;
		ReversiMove m = null;
		int column = 0, row = 0;
		
		while(!moved){
			do { 
				row    = (int) ((Math.random() * Resources.DIMY_REVERSI) + 1);
				column = (int) ((Math.random() * Resources.DIMX_REVERSI) + 1);
				 
				if (board.getPosition(column, row) == Counter.EMPTY) {
					valid = true;
				}
			}  while(!valid);
			m = new ReversiMove(column, row, counter);
			m.checkHorizontal(board);
			m.checkVertical(board);
			m.checkDiagonal1(board);
			m.checkDiagonal2(board);
			if(m.getListLength() > 0){
				moved = true;
			}
		}
		return m;
	}
}
