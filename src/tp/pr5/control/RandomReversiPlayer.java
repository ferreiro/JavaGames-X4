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
		
		do {
			row    = (int) ((Math.random() * Resources.DIMY_REVERSI) + 1);
			column = (int) ((Math.random() * Resources.DIMX_REVERSI) + 1);
			 
			if (board.getPosition(column, row) == Counter.EMPTY) {
				
				int total = 0;
				boolean keepMove = false;

				m = new ReversiMove(column, row, counter);
				
				total += m.checkHorizontal(board, column, row, true, keepMove);	// True  = Left
				total += m.checkHorizontal(board, column, row, false, keepMove);	// False = Right
				total += m.checkVertical(board, column, row, true, keepMove); 	// True  = UP	
				total += m.checkVertical(board, column, row, false, keepMove); 	// False = Down	
				total += m.checkDiagonal1(board, column, row, true, keepMove); 	// True = Top Left
				total += m.checkDiagonal1(board, column, row, false, keepMove);  // False = Bottom Right
				total += m.checkDiagonal2(board, column, row, true, keepMove); 	// True = Top Right
				total += m.checkDiagonal2(board, column, row, false, keepMove);  // False = Bottom Left

				if (total >= 1) { // => Si se ha formado al menos una check, significa que hay alguna celda flrmDa, por tanto, el movimiento es valido
					valid = true;	 
				}	 
			}
		} while(!valid);
		 
		return m;
	}
}
