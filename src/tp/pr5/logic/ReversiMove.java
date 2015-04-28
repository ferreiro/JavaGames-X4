package tp.pr5.logic;

import tp.pr5.Resources.Resources;

public class ReversiMove extends Move {

	public ReversiMove(int moveColumn, int moveRow, Counter moveColour) {
		super(moveColour, moveColumn);
		super.row = moveRow;
	}

	@Override
	public boolean executeMove(Board b) throws InvalidMove {
		boolean valid = false;
		
		if ((column >= 1 && column <= Resources.DIMX_REVERSI) && (row >= 1 && row <= Resources.DIMY_REVERSI)) {
			if (b.getPosition(column, row) == Counter.EMPTY) {
				valid = true;
				//TODO: terminnar execute move
				
				valid = checkHorizontal(b, column, row, true); // True = left Part
				valid = checkHorizontal(b, column, row, false); // False = right part
				valid = checkVertical(b, column, row, true); // True = Up part
				valid = checkVertical(b, column, row, false); // True = Up part	
				
			}
		}	
		 
		return valid;
	}

	// CheckHorizontal: Function to Check left and right colors given a fixed position
	
	public boolean checkHorizontal(Board b, int x, int y, boolean left) {
		boolean valid = false;
		int auxColumn = x, total = 0;
		Counter color = b.getPosition(x, y);
 
		if (left) {
			while((auxColumn > 1) && (b.getPosition(auxColumn - 1, y) != color)) {
				total += 1; auxColumn -= 1; 
			} 
			if ((total >= 1) && (b.getPosition(auxColumn - 1, y) == color)) {
				System.out.println("Valid move!"); // DEBUG
				valid = true; // If there is unless one Counter of the other color and the last counter is the same as the move color
				// TODO Si se han formado celdas y la última ficha es del mismo color que la primera 
				// TODO: Marcar las celdas
				// Posición final para guardar: (auxColumn, y)
			}
		}
		else {
			while((auxColumn < b.getWidth()) && (b.getPosition(auxColumn + 1, y) != color)) {
				total += 1; auxColumn += 1; 
			} 
			if ((total >= 1) && (b.getPosition(auxColumn + 1, y) == color)) {
				System.out.println("Valid move!"); // DEBUG
				valid = true; // If there is unless one Counter of the other color and the last counter is the same as the move color
				// TODO Si se han formado celdas y la última ficha es del mismo color que la primera 
				// TODO: Marcar las celdas
				// Posición final para guardar: (auxColumn, y)
			}			
		}
 
		return valid;
	}

	// CheckVertical: Function to Check left and right colors given a fixed position

	public boolean checkVertical(Board b, int x, int y, boolean up) {
		boolean valid = false;
		int auxRow = y, total = 0;
		Counter color = b.getPosition(x, y);
		 
		if (up) {
			while((auxRow > 1) && (b.getPosition(x, auxRow - 1) != color)) {
				total += 1; auxRow -= 1; 
			} 
			if ((total >= 1) && (b.getPosition(x, auxRow - 1) == color)) {
				System.out.println("Valid move!"); // DEBUG
				valid = true; // If there is unless one Counter of the other color and the last counter is the same as the move color
				// TODO Si se han formado celdas y la última ficha es del mismo color que la primera 
				// TODO: Marcar las celdas
				// Posición final para guardar: (x, auxRow)
			}
		} 
		else {
			while((auxRow < b.getHeight()) && (b.getPosition(x, auxRow + 1) != color)) {
				total += 1; auxRow += 1; 
			} 
			if ((total >= 1) && (b.getPosition(x, auxRow + 1) == color)) {
				System.out.println("Valid move!"); // DEBUG
				valid = true; // If there is unless one Counter of the other color and the last counter is the same as the move color
				// TODO Si se han formado celdas y la última ficha es del mismo color que la primera 
				// TODO: Marcar las celdas
				// Posición final para guardar: (auxColumn, y)
			}			
		} 
		return valid;
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
