package tp.pr4.logic;

import tp.pr4.Resources.Resources;

public class Connect4Move extends Move {
	 
	public Connect4Move(int column, Counter counter) {
		super(counter, column); 
	}

	public boolean executeMove(Board board) throws InvalidMove {
		boolean validMove = false;
		int firstFreeRow = 1;

		if ((column >= 1) && (column <= Resources.DIMX_CONNECT4)) {				
			firstFreeRow = Resources.freeRowPosition(column, board);

			if (firstFreeRow > - 1) {		
				validMove = true;
				board.setPosition(column, firstFreeRow, super.getPlayer()); 
			}
			else{
				throw new InvalidMove("Invalid move: column number " + column + " is already full.");
			}
		}
		else {
			throw new InvalidMove("Invalid move: column number " + column + " is not on the board.");
		}
		return validMove;
	}

	public void undo(Board board) {
		int columnToUndo, rowToUndo;
		
		columnToUndo = column;
		rowToUndo = Resources.occupiedRowPosition(board, columnToUndo); 
		board.setPosition(columnToUndo, rowToUndo, Counter.EMPTY); 
	}
	
	public int getColumn() {
		return this.column;
	}
}