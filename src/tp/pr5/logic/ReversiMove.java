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
		
		if ((column >= 1 && column <= Resources.DIMX_REVERSI) && 
			(row >= 1 && row <= Resources.DIMY_REVERSI) 	  && 
			(b.getPosition(column, row) == Counter.EMPTY)) { 
				
				// Un movimiento es válido si puede revertir alguna celda formada
				// Y además las posiciones están dentro del tablero y es una celda vacía
				
				valid = checkHorizontal(b, column, row, true); // True = left Part
					if(!valid) valid = checkHorizontal(b, column, row, false); // False = right part
						if (!valid) valid = checkVertical(b, column, row, true); // True = Up part
							if (!valid) valid = checkVertical(b, column, row, false); // True = Up part	
								if (!valid) valid = checkDiagonal(b, column, row, true); // True = topLeft
									if (!valid) valid = checkDiagonal(b, column, row, false); // False = Bottom Right
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
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.

				// TODO: Guardar en el array la última posición válida de este movimiento (auxColumn, y)
				
			}
		}
		else {
			while((auxColumn < b.getWidth()) && (b.getPosition(auxColumn + 1, y) != color)) {
				total += 1; auxColumn += 1; 
			} 
			if ((total >= 1) && (b.getPosition(auxColumn + 1, y) == color)) {
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.

				// TODO: Guardar en el array la última posición válida de este movimiento (auxColumn, y)
		
			}			
		}
 
		return valid;
	}

	// CheckVertical: Function to Check top and bottom colors given a fixed position
	public boolean checkVertical(Board b, int x, int y, boolean up) {
		boolean valid = false;
		int auxRow = y, total = 0;
		Counter color = b.getPosition(x, y);
		 
		if (up) {
			while((auxRow > 1) && (b.getPosition(x, auxRow - 1) != color)) {
				total += 1; auxRow -= 1; 
			} 
			if ((total >= 1) && (b.getPosition(x, auxRow - 1) == color)) {
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.

				// TODO: Guardar en el array la última posición válida de este movimiento (column, auxRow)
				
			}
		} 
		else {
			while((auxRow < b.getHeight()) && (b.getPosition(x, auxRow + 1) != color)) {
				total += 1; auxRow += 1; 
			} 
			if ((total >= 1) && (b.getPosition(x, auxRow + 1) == color)) {
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.

				// TODO: Guardar en el array la última posición válida de este movimiento (column, auxRow)
				
			}			
		} 
		return valid;
	}
	
	// CheckHorizontal: Function to Check diagonals given a fixed position
	public boolean checkDiagonal(Board b, int x, int y, boolean topLeft) {
		boolean valid = false;
		int auxColumn = x, auxRow = x, total = 0;
		Counter color = b.getPosition(x, y);
 
		if (topLeft) {
			while((auxColumn > 1) && (auxRow > 1) && (b.getPosition(auxColumn - 1, auxRow - 1) != color)) {
				total += 1; auxColumn -= 1; auxRow -= 1; 
			} 
			if ((total >= 1) && (b.getPosition(auxColumn - 1, auxRow - 1) == color)) {
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.

				// TODO: Guardar en el array la última posición válida de este movimiento (auxColumn, auxRow)
				
			}
		}
		else {
			while((auxColumn < b.getWidth()) && (auxRow < b.getHeight()) && (b.getPosition(auxColumn + 1, auxRow + 1) != color)) {
				total += 1; auxColumn += 1; auxRow += 1; 
			} 
			if ((total >= 1) && (b.getPosition(auxColumn + 1, auxRow + 1) == color)) {
				valid = true; // Se pueden formar celdas entre medias y hay una ficha en el extremo, del mismo color que la original.

				// TODO: Guardar en el array la última posición válida de este movimiento (auxColumn, auxRow)
				
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
