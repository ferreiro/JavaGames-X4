package tp.pr5.control;

import tp.pr5.Resources.Resources;
import tp.pr5.logic.Board;
import tp.pr5.logic.Counter;
import tp.pr5.logic.Move;
import tp.pr5.logic.ReversiMove;

public class RandomReversiPlayer implements Player {

	@Override
	public Move getMove(Board board, Counter counter) {
		boolean valid = false, keepMove = false;
		ReversiMove m = null;
		int column = 0, row = 0, total;
		
		do {
			row    = (int) ((Math.random() * Resources.DIMY_REVERSI) + 1);
			column = (int) ((Math.random() * Resources.DIMX_REVERSI) + 1);
			 
			if (board.getPosition(column, row) == Counter.EMPTY) {
				
				m = new ReversiMove(column, row, counter);
				total = 0; 				
				total += m.checkHorizontal(board, column, row, keepMove);	// True  = Left
				total += m.checkVertical(board, column, row, keepMove); 	// True  = UP	
				total += m.checkDiagonal1(board, column, row, keepMove); 	// True = Top Left
				total += m.checkDiagonal2(board, column, row, keepMove); 	// True = Top Right

				if (total >= 1) { 
					valid = true;	 
				}	 
			}
			
		} while(!valid);
		 
		return m;
	}
}
