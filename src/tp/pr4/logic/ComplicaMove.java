package tp.pr4.logic;

import tp.pr4.Resources.Resources;


public class ComplicaMove extends Move {
	protected Counter lostMove; //	Last lost movement 
		
	// Constructor for a single Movement on Complica
	
	public ComplicaMove(int column, Counter counter) {
		super(counter, column); 
	}

	public boolean executeMove(Board board) throws InvalidMove {
		boolean validMove = false;
		int firstFreeRow = 1;
		lostMove = Counter.EMPTY;

		if ((column >= 1) && (column <= Resources.DIMX_COMPLICA)) {				
			firstFreeRow = Resources.freeRowPosition(column, board); // hay que cambiar esto

			if (firstFreeRow > - 1) {
				validMove = true;
				board.setPosition(column, firstFreeRow, super.getPlayer());
			}
			else if (firstFreeRow == - 1) {
				validMove = true;
				lostMove = board.getPosition(column, board.getHeight()); 
				Resources.moveColumnDown(board, column);
				board.setPosition(column, 1, super.getPlayer());
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

		if (lostMove == Counter.EMPTY) {
			rowToUndo = Resources.occupiedRowPosition(board, columnToUndo); 
			board.setPosition(columnToUndo, rowToUndo, Counter.EMPTY);
		}
		else {
			Resources.moveColumnUp(board, column);
			board.setPosition(column, board.getHeight(), lostMove);
		}	
	}

	@Override
	public int getColumn() {
		return this.column;
	}
	
}
