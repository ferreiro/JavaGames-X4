package tp.pr4.logic;

import tp.pr4.Resources.Resources;

public class ComplicaMove extends Move {
	protected Counter lostMove; //	Last lost movement 
		
	public ComplicaMove(int column, Counter counter) {
		super(counter, column); 
	}

	// Executes a single Mvement on Complica
	
	public boolean executeMove(Board board) throws InvalidMove {
		int firstFreeRow = 1;
		boolean validMove = false;

		this.lostMove = Counter.EMPTY; // Suponemos Celda donde el usuario quiere poner ficha vacía. Así que guardamos el movimiento actual como empty

		if ((column >= 1) && (column <= Resources.DIMX_COMPLICA)) {				
			
			validMove = true;
			firstFreeRow = Resources.freeRowPosition(column, board);

			if (firstFreeRow > -1) // Alguna celda está vacia. Así que ponemos la ficha en esa celda
				board.setPosition(column, firstFreeRow, super.getPlayer());
			
			else // Columna llena: Guardar en lostMove la celda actual y mover fichas
				this.lostMove = board.getPosition(column, board.getHeight()); 
				Resources.moveColumnDown(board, column);
				board.setPosition(column, 1, super.getPlayer());
		}
		return validMove;
	}
	
	// Do a single undo for a complica Move
	
	public void undo(Board board) {
		int columnToUndo = column, rowToUndo;
		
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
