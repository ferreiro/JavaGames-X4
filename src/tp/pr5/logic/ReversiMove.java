package tp.pr5.logic;

import tp.pr5.Resources.Resources;

public class ReversiMove extends Move {

	public ReversiMove(int moveColumn, int moveRow, Counter moveColour) {
		super(moveColour, moveColumn);
		super.row = moveRow;
	}

	@Override
	public boolean executeMove(Board board) throws InvalidMove {
		boolean validMove = false;
		
		if ((column >= 1 && column <= Resources.DIMX_REVERSI) && (row >= 1 && row <= Resources.DIMY_REVERSI)) {
			if (board.getPosition(column, row) == Counter.EMPTY) {
				validMove = true;
				//TODO: terminnar execute move
			}
		}	
		
		return validMove;
	}

	@Override
	public void undo(Board board) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getColumn() {
		return this.column;
	}
	public int getRow() {
		return this.row;
	}

}
